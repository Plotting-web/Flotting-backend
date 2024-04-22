package com.flotting.api.history.service;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataMaker {

    @Autowired
    private UserService userService;

    private String filePath = "/Users/jangsoobin/Downloads/testData.csv";

    public List<UserResponseDto> makeUserData() {
        List<UserResponseDto> result = new ArrayList();
        List<String> names = List.of("A", "B", "C", "D", "E", "F", "G");
        List<JobEnum> jobs = List.of(JobEnum.PROFESSIONAL, JobEnum.MID_MAJOR_COMPANY, JobEnum.BUSNINESS, JobEnum.MID_MAJOR_COMPANY, JobEnum.PUBLIC_COMPANY, JobEnum.PROFESSIONAL);
        List<Integer> ages = List.of(1,18,3,4,5,6);
        List<String> phoneNumbers = List.of("0101111", "010333", "010222", "010444", "010555", "010666");
        List<Integer> heights = List.of(1,2,180,4,5,6);
        List<Boolean> smokings = List.of(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        List<BodyEnum> bodies = List.of(BodyEnum.RELIABLE, BodyEnum.SLIM, BodyEnum.SOLID, BodyEnum.CHUBBY, BodyEnum.MUSCULAR, BodyEnum.NORMAL);
        List<String> charms = List.of("a", "b", "c", "d", "e", "f");
        List<String> detailJobs = List.of("a", "b", "c", "d", "e", "f");
        List<DrinkingEnum> drinkings = List.of(DrinkingEnum.ONE_WEEK, DrinkingEnum.THREE_WEEK, DrinkingEnum.TWO_WEEK, DrinkingEnum.ONE_WEEK, DrinkingEnum.TWO_WEEK, DrinkingEnum.THREE_WEEK);
        List<EducationEnum> educations = List.of(EducationEnum.COLLEGE_ACADEMY_ATTENDING, EducationEnum.COLLEGE_ATTENDING, EducationEnum.JUNIOR_COLLEGE_ATTENDING, EducationEnum.COLLEGE_GRADUATION, EducationEnum.JUNIOR_COLLEGE_GRADUATION, EducationEnum.HIGH_SCHOOL_GRADUATION);
        List<String> emails = List.of("naver", "daum", "kakao", "google", "never", "gagle");
        List<GradeEnum> grades = List.of(GradeEnum.D, GradeEnum.D, GradeEnum.G, GradeEnum.P, GradeEnum.G, GradeEnum.D);
        List<List<String>> hobbies = List.of(List.of("a"), List.of("a"), List.of("a"), List.of("a"), List.of("a"), List.of("a"));
        List<LocationEnum> locations = List.of(LocationEnum.GGYEONGGI_EAST, LocationEnum.GGYEONGGI_EAST, LocationEnum.GGYEONGGI_EAST, LocationEnum.SEOUL_EAST, LocationEnum.SEOUL_NORTH, LocationEnum.SEOUL_WEST);
        List<String> loveValues = List.of("a", "b", "c", "d", "e", "f");
        List<String> nickNames = List.of("a", "b", "c", "d", "e", "f");
        List<AppliedPathEnum> paths = List.of(AppliedPathEnum.ETC, AppliedPathEnum.FRIP, AppliedPathEnum.FRIP, AppliedPathEnum.FRIP, AppliedPathEnum.FRIP, AppliedPathEnum.RECOMMEND);
        List<GenderEnum> genders = List.of(GenderEnum.F, GenderEnum.M, GenderEnum.F, GenderEnum.M, GenderEnum.F, GenderEnum.M);
        List<PreferenceEnum> preferences = List.of(PreferenceEnum.AGE, PreferenceEnum.DISTANCE, PreferenceEnum.HEIGHT, PreferenceEnum.JOB, PreferenceEnum.HEIGHT, PreferenceEnum.JOB);
        List<List<String>> preferenceValues = List.of(List.of("18","19"), List.of(LocationEnum.SEOUL_EAST.name(), LocationEnum.GGYEONGGI_EAST.name()), List.of("180"), List.of(JobEnum.BUSNINESS.name()), List.of("170"), List.of(JobEnum.PROFESSIONAL.name()));
        List<String> preferenceDetails = List.of("a", "b", "c", "d", "e", "f");
        List<String> recommendUserNames = List.of("a", "b", "c", "d", "e", "f");
        List<String> URIs = List.of("a", "b", "c", "d", "e", "f");
        List<Integer> totalScores = List.of(100,100,45,43,23,12);
        List<Integer> faceScores = List.of(1,1,1,1,1,1);

        IntStream.range(0, 6).forEach(idx -> {
            UserSimpleRequestDto simpleRequestDto = UserSimpleRequestDto.builder()
                    .name(names.get(idx))
                    .job(jobs.get(idx).name())
                    .age(ages.get(idx))
                    .phoneNumber(phoneNumbers.get(idx))
                    .password("1234")
                    .build();
            UserSimpleResponseDto userSimpleResponseDto = userService.saveSimpleUserInfo( simpleRequestDto);

            UserDetailRequestDto detailRequestDto = UserDetailRequestDto.builder()
                    .height(heights.get(idx))
                    .smoking(smokings.get(idx))
                    .detailJob(detailJobs.get(idx))
                    .drinking(drinkings.get(idx).name())
                    .education(educations.get(idx).name())
                    .email(emails.get(idx))
                    .grade(grades.get(idx).name())
                    .hobby(hobbies.get(idx))
                    .location(locations.get(idx).name())
                    .nickName(nickNames.get(idx))
                    .path(paths.get(idx).name())
                    .gender(genders.get(idx).name())
                    .recommendUserName(recommendUserNames.get(idx))
                    .URI(URIs.get(idx))
                    .build();
            UserDetailResponseDto userDetailResponseDto = userService.saveDetailUserInfo(userSimpleResponseDto.getUserNo(), detailRequestDto);
            result.add(new UserResponseDto(userSimpleResponseDto, userDetailResponseDto));
        });
        return result;
    }

    public List<UserResponseDto> makeUserDataByCsv() throws ParseException {
        List<UserResponseDto> result = new ArrayList();
        try {
            BufferedReader bf = Files.newBufferedReader(Paths.get(filePath), Charset.forName("UTF-8"));
            String line = "";
            while((line = bf.readLine()) != null) {
                List<String> dataList = new ArrayList<>();
                String array[] = line.split(",");
                dataList = Arrays.asList(array);

                List<String> titleList = List.of("name", "age", "height", "gender", "location",
                        "phoneNumber", "phoneNumber", "appliedPath", "recommendedName", "loveValues",
                        "charm", "preference", "hobby", "datePreference", "lifeStyle",
                        "미래연인에게하고픈말", "nickName", "job", "detailJob", "education",
                        "smoking", "drinking", "grade", "approvedAt");

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
                        .grade(GradeEnum.byValue(array[22]).name())
                        .hobby(List.of(array[12]))
                        .location(LocationEnum.byValue(array[4]).name())
                        .nickName(array[16])
                        .path(AppliedPathEnum.byValue(array[7]).name())
                        .gender("여성".equals(array[3]) ? GenderEnum.F.name() : GenderEnum.M.name())
                        .recommendUserName(array[8])
                        .URI("")
                        .approvedAt(LocalDate.parse(array[23], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build();
                UserDetailResponseDto userDetailResponseDto = userService.saveDetailUserInfo(userSimpleResponseDto.getUserNo(), detailRequestDto);
                result.add(new UserResponseDto(userSimpleResponseDto, userDetailResponseDto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
