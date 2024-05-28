package com.flotting.api.user;

import com.flotting.api.manager.model.ApproveRequestDto;
import com.flotting.api.manager.service.ManagerService;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.*;
import com.flotting.api.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@SpringBootTest
public class SampleDataMaker {
    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    public List<UserResponseDto> makeUserData(boolean isCSVFile) {
        managerService.makeSampleData();
        List<String> datas = new ArrayList<>();
        if(isCSVFile){
            datas = makeUserDataByCsv("/Users/jangsoobin/Downloads/testData.csv");
        } else {
            datas = getStringData();
        }
        return makeUserData(datas);
    }

    private List<String> getStringData() {
        List<String> datas = new ArrayList<>();
        datas.add("박서연,40,165,남성,서울 북부,37482910,1037482910,프립,,,,,,,,,푸른하늘꿈,교육직,초등교사,대학교 졸업,N,자주 마심,D,2024-01-07,애교있는,산책,활성,891126");
        datas.add("윤지영,37,153,여성,서울 동부,56473821,1056473821,네이버 스토어,,ISFP,,,,,,,무지개빛꿈,일반(공기업/공무원),주택도시보증공사,대학교 졸업,N,거의 안 마심,D,2024-03-22,애교있는,산책,활성,830722");
        datas.add("이주안,31,158,여성,서울 서부,83729109,1083729109,네이버 스토어,,ESFP,,,,,,,눈부신아침,일반(중견기업),농협 경제지주 전문직,대학교 졸업,N,가끔 마심,D,2024-01-30,애교있는,산책,활성,840926");
        datas.add("송하율,36,167,여성,서울 동부,84736295,1084736295,네이버 스토어,,INFJ,,,,,,,푸른바다의전설,일반(공기업/공무원),분당서울대학교병원(공공기관) 행정직,대학교 졸업,N,아예 안 마심,P,2024-01-20,애교있는,산책,활성,950702");
        datas.add("강연우,35,160,여성,서울 서부,29381042,1029381042,네이버 스토어,,INFJ,,,,,,,달콤커피,일반(공기업/공무원),교통안전공단,고등학교 졸업,N,아예 안 마심,P,2024-01-23,애교있는,산책,활성,940610");
        datas.add("강서현,35,168,여성,경기 동부,19238475,1019238475,와디즈,,ISFJ,,,,,,,눈부신날에,일반(공기업/공무원),KATRI시험연구원(한국의류시험연구원),대학교 졸업,Y,가끔 마심,P,2024-01-24,애교있는,산책,활성,930420");
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
                    .URL("")
//                    .approvedAt(LocalDateTime.parse(array[23] + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .mbti(array[9])
                    .character(List.of(CharacterEnum.byValue(array[24]).name()))
                    .preferredDate(array[25])
                    .userStatus(UserStatusEnum.byValue(array[26]).name())
                    .job("PROFESSIONAL")
                    .birthday(array[27])
                    .build();
            UserResponseDto userResponseDto = userService.saveDetailUserInfo(userSimpleResponseDto.getUserNo(), detailRequestDto);
            GradeEnum grade = StringUtils.isNotEmpty(array[22]) && Objects.nonNull(GradeEnum.byValue(array[22])) ? GradeEnum.byValue(array[22]) : GradeEnum.G;
            ApproveRequestDto approveRequestDto = new ApproveRequestDto(grade.name(), "comment", 1L);
            managerService.approveInfo(userSimpleResponseDto.getUserNo(), approveRequestDto);
            result.add(userResponseDto);
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
