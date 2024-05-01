package com.flotting.api.history.service;

import com.flotting.api.manager.model.ApproveRequestDto;
import com.flotting.api.manager.service.ManagerService;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.*;
import com.flotting.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataMaker {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    public List<UserResponseDto> makeUserData(boolean isCSVFile) throws ParseException {
        managerService.makeSampleData();
        List<String> datas = new ArrayList<>();
        if(isCSVFile){
            datas = makeUserDataByCsv("/Users/jangsoobin/Downloads/testData1.csv");
        } else {
            datas = getStringData();
        }
        return makeUserData(datas);
    }

    private List<String> getStringData() {
        List<String> datas = new ArrayList<>();
        datas.add("김민준,1997,166,남성,서울 북부,48392047,1048392047,프립,,,,,,,,,별빛여행자,일반(중견기업),개발자 - 유비케어,대학교 졸업,N,자주 마심,G,2024-01-01,애교있는,산책,활성");
        datas.add("김지안,1998,171,남성,서울 동부,19283745,1019283745,소모임,,ISFP,,,,,,,향기로운순간,일반(중소기업),아트디렉터 - Jarvis Products Corporation,대학교 졸업,N,거의 안 마심,G,2024-01-02,애교있는,산책,승인전&카카오로그인만");
        datas.add("이하윤,1999,172,남성,서울 북부,19283746,1019283746,프립,,,,,,,,,초록바람,일반(공기업/공무원),산림청 국립산림과학원 국제협력 업무,대학교 졸업,N,자주 마심,G,2024-01-04,애교있는,산책,프로필등록완료&승인전");
        datas.add("이태윤,2000,182,남성,서울 동부,74839215,1074839215,소모임,,ISFP,,,,,,,빛나는오로라,금융직,직장인 - 코스콤,대학교 졸업,N,거의 안 마심,G,2024-01-05,애교있는,산책,반려");
        datas.add("박서연,1999,165,남성,서울 북부,37482910,1037482910,프립,,,,,,,,,푸른하늘꿈,교육직,초등교사,대학교 졸업,N,자주 마심,D,2024-01-07,애교있는,산책,탈퇴");
        datas.add("박하람,1997,166,남성,서울 동부,29384701,1029384701,소모임,,ISFP,,,,,,,소나기후무지개,일반(중소기업),아이비에스인더스트리부동산개발.건물관리 회사(총무.사무직),대학교 졸업,N,거의 안 마심,G,2024-01-08,애교있는,산책,휴면");
        datas.add("최준호,2000,184,남성,서울 북부,58293746,1058293746,프립,,,,,,,,,달콤한소나타,일반(대기업),CJ대한통운 AI빅데이터연구,대학교 졸업,N,자주 마심,D,2024-01-10,애교있는,산책,강제탈퇴");
        return datas;
    }

    public List<UserResponseDto> makeUserData(List<String> datas) {
        List<UserResponseDto> result = new ArrayList();
        for(String data : datas) {
            String array[] = data.split(",");
            UserSimpleRequestDto simpleRequestDto = UserSimpleRequestDto.builder()
                    .name(array[0])
                    .age(Integer.parseInt(array[1]))
                    .job(JobEnum.byValue(array[17]).name())
                    .phoneNumber(array[6])
                    .password("1234")
                    .build();
            UserSimpleResponseDto userSimpleResponseDto = userService.saveSimpleUserInfo( simpleRequestDto);

            UserDetailRequestDto detailRequestDto = UserDetailRequestDto.builder()
                    .height(Integer.parseInt(array[2]))
                    .smoking("Y".equals(array[20]) ? true : false)
                    .detailJob(array[18])
                    .drinking(DrinkingEnum.byValue(array[21]).name())
                    .education(EducationEnum.byValue(array[19]).name())
                    .email(array[16])
                    .hobby(List.of("EXERCISE", "SELF_IMPROVEMENT", "READING"))
                    .location(LocationEnum.byValue(array[4]).name())
                    .nickName(array[16])
                    .path(AppliedPathEnum.byValue(array[7]).name())
                    .gender("여성".equals(array[3]) ? GenderEnum.F.name() : GenderEnum.M.name())
                    .recommendUserName(array[8])
                    .URI("")
//                    .approvedAt(LocalDateTime.parse(array[23] + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .mbti(array[9])
                    .character(List.of(CharacterEnum.byValue(array[24]).name()))
                    .preferredDate(array[25])
                    .userStatus(UserStatusEnum.byValue(array[26]).name())
                    .job("PROFESSIONAL")
                    .build();
            UserDetailResponseDto userDetailResponseDto = userService.saveDetailUserInfo(userSimpleResponseDto.getUserNo(), detailRequestDto);

            ApproveRequestDto approveRequestDto = new ApproveRequestDto("G", "comment", 1L);
            managerService.approveInfo(userSimpleResponseDto.getUserNo(), approveRequestDto);
            result.add(new UserResponseDto(userSimpleResponseDto, userDetailResponseDto));
        }
        return result;
    }

    private List<String> makeUserDataByCsv(String filePath) {
        List<String> dataList = new ArrayList();
        try {
            BufferedReader bf = Files.newBufferedReader(Paths.get(filePath), Charset.forName("UTF-8"));
            String line = "";
            while((line = bf.readLine()) != null) {
                dataList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
