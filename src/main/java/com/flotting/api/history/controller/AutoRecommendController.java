package com.flotting.api.history.controller;

import com.flotting.api.history.model.AutoRecommendedData;
import com.flotting.api.history.service.AutoRecommendService;
import com.flotting.api.history.service.SampleDataMaker;
import com.flotting.api.util.service.ExcelService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AutoRecommendController {

    private  final AutoRecommendService autoRecommendService;
    private final SampleDataMaker sampleDataMaker;
    private final ExcelService excelService;

    @PostConstruct
    public void makeUserData() throws ParseException {
//        sampleDataMaker.makeUserDataByCsv();
    }

    @GetMapping("/auto-recommend/download")
    public void downloadRecommendedData(@RequestParam(name = "phoneNumber") String phoneNumber, HttpServletResponse response) {
        List<AutoRecommendedData> datas = autoRecommendService.createAutoRecommend(phoneNumber);
        excelService.downloadExcel(datas, response);
    }

    @GetMapping("/auto-recommend/multiple/download")
    public void downloadRecommendedDatas(@RequestParam(name = "phoneNumber") List<String> phoneNumbers, HttpServletResponse response) {
        ArrayList<AutoRecommendedData> result = new ArrayList<>();
        phoneNumbers.forEach(phoneNumber -> {
            List<AutoRecommendedData> datas = autoRecommendService.createAutoRecommend(phoneNumber);
            result.addAll(datas);
        });
        excelService.downloadExcel(result, response);
    }

    /**
     * 조회 가능한 자동추천 이력 리스트 조회
     * @param userId 수신자
     * @return
     */
    @GetMapping("/auto-recommend/profile/{userId}")
    public List<AutoRecommendedData> getAutoRecommendedProfileList(@PathVariable(name = "userId") Long userId) {
        return autoRecommendService.getAccessibleAutoRecommendedProfile(userId);
    }
}
