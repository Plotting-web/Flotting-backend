package com.flotting.api.user.model;

import com.flotting.api.user.enums.*;
import com.flotting.api.util.ExcelDownloadable;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResponseDto implements ExcelDownloadable {

    @Schema(description = "simpleProfileId")
    private Long userNo;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "나이")
    private Integer age;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "계정상태")
    private String userStatus;

    @Schema(description = "직업", allowableValues = {"PROFESSIONAL", "MID_MAJOR_COMPANY", "FINANCE", "PUBLIC_COMPANY",
            "EDU", "LAB", "MEDICAL", "BUSNINESS","SMALL_COMPANY", "FREELANCER", "STUDENT",
    })
    private String job;

    @Schema(description = "신장")
    private Integer height;

    @Schema(description = "성별", allowableValues={"F", "M"})
    private String gender;

    @Schema(description = "거주지", allowableValues = {"SEOUL_NORTH", "SEOUL_SOUTH", "SEOUL_WEST", "SEOUL_EAST",
            "GGYEONGGI_NORTH", "GGYEONGGI_SOUTH", "GGYEONGGI_WEST", "GGYEONGGI_EAST"
    })
    private String location;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "신청 경로", allowableValues = {"SMALL_CLASS_C", "SMALL_CLASS_M", "FRIP", "MOONTO", "INSTA", "RECOMMEND", "NAVER", "ETC", "PORTAL"})
    private String appliedPath;

    @Schema(description = "추천인 이름")
    private String recommendUserName;

    @Schema(description = "취미")
    private List<String> hobby;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "직장명")
    private String detailJob;

    @Schema(description = "졸업 이력", allowableValues = {"HIGH_SCHOOL_GRADUATION", "JUNIOR_COLLEGE_ATTENDING", "JUNIOR_COLLEGE_GRADUATION", "COLLEGE_ATTENDING", "COLLEGE_GRADUATION", "COLLEGE_ACADEMY_ATTENDING", "COLLEGE_ACADEMY_GRADUATION"})
    private String education;

    @Schema(description = "흡연 여부")
    private Boolean smoking;

    @Schema(description = "음주 빈도", allowableValues = {"THREE_WEEK", "TWO_WEEK", "ONE_WEEK", "ZERO_WEEK"})
    private String drinking;

    @Schema(description = "등급", allowableValues = {"G", "D", "P"})
    private String grade;

    @Schema(description = "2차 프로필 id")
    private Long detailProfileId;

    @Schema(description = "승인일자")
    private LocalDate approvedAt;

    @Schema(description = "매니저 코멘트")
    private String managerComment;

    @Schema(description = "mbti")
    private String mbti;

    @Schema(description = "내성격")
    private List<String> character;

    @Schema(description = "선호데이트")
    private String preferredDate;

    @Schema(description = "라이프스타일")
    private String lifeStyle;

    @Schema(description = "하고싶은말")
    private String somethingWantToSay;

    @Schema(description = "생일")
    private String birthday;

    @Schema(description = "사진경로")
    private List<String> profileImageURIs;

    @Schema(description = "거절사유")
    private String rejectedReason;

    public UserResponseDto(UserSimpleResponseDto simpleInfo, UserDetailResponseDto detailInfo) {
        this.userNo = simpleInfo.getUserNo();
        this.age = simpleInfo.getAge();
        this.phoneNumber = simpleInfo.getPhoneNumber();
        this.name = simpleInfo.getName();;
        this.appliedPath = detailInfo.getAppliedPath();
        this.detailJob = detailInfo.getDetailJob();
        this.drinking = detailInfo.getDrinking();
        this.education = detailInfo.getEducation();
        this.email = detailInfo.getEmail();
        this.grade = detailInfo.getGrade();;
        this.height = detailInfo.getHeight();
        this.hobby = detailInfo.getHobby();
        this.location = detailInfo.getLocation();
        this.nickName = detailInfo.getNickName();;
        this.gender = detailInfo.getGender();
        this.smoking = detailInfo.getSmoking();
        this.recommendUserName = detailInfo.getRecommendUserName();
        this.detailProfileId = detailInfo.getSeq();
        this.approvedAt = detailInfo.getApprovedAt();
        this.birthday = detailInfo.getBirthday();
        this.managerComment = detailInfo.getManagerComment();
        this.preferredDate= detailInfo.getPreferredDate();
        this.lifeStyle = detailInfo.getLifeStyle();
        this.somethingWantToSay = detailInfo.getSomethingWantToSay();
        this.rejectedReason = detailInfo.getRejectedReason();
        this.userStatus = detailInfo.getUserStatus();
        this.job = detailInfo.getJob().name();
    }

    @QueryProjection
    public UserResponseDto(Long userNo, String name, Integer age, String phoneNumber, UserStatusEnum userStatus,
                           JobEnum job, Integer height, GenderEnum gender, LocationEnum location, String email,
                           AppliedPathEnum appliedPath, String recommendUserName, List<HobbyEnum> hobby, String nickName,
                           String detailJob, EducationEnum education, Boolean smoking, DrinkingEnum drinking,  GradeEnum grade, Long detailProfileId,
                           LocalDate approvedAt, String managerComment, String mbti, List<CharacterEnum> character, String preferredDate, String lifeStyle, String somethingWantToSay,
                           String birthday, List<String> profileImageURIs, String rejectedReason) {
        this.userNo = userNo;
        this.age = age;
        this.job= job.name();
        this.userStatus = userStatus.name();
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.appliedPath = appliedPath.name();;
        this.detailJob = detailJob;
        this.drinking = drinking.name();
        this.education = education.name();
        this.email = email;
        this.grade = grade.name();
        this.height = height;
        this.hobby = hobby.stream().map(HobbyEnum::name).collect(Collectors.toList());
        this.location = location.name();;
        this.nickName = nickName;
        this.gender = gender.name();;
        this.smoking = smoking;
        this.recommendUserName = recommendUserName;
        this.detailProfileId = detailProfileId;
        this.approvedAt = approvedAt;
        this.managerComment = managerComment;
        this.mbti = mbti;
        this.character = character.stream().map(CharacterEnum::name).collect(Collectors.toList());;
        this.lifeStyle = lifeStyle;
        this.preferredDate = preferredDate;
        this.somethingWantToSay = somethingWantToSay;
        this.birthday = birthday;
        this.profileImageURIs = profileImageURIs;
        this.rejectedReason = rejectedReason;
    }


    @Override
    public String[] getHeaders() {
        String[] headers = {"이름", "나이", "전화번호", "계정상태", "직업", "신장", "성별", "거주지", "이메일", "신청 경로", "추천인 이름",
                "선호도1위", "선호 구체적 설명", "나의 매력", "나의 연애관", "취미", "닉네임", "체형", "직장명", "졸업 이력", "흡연 여부", "음주 빈도", "등급"};
        return headers;
    }

    @Override
    public String[] getCellDatas() {
        String[] cellDatas = {
                this.name, String.valueOf(this.age), this.phoneNumber, this.userStatus, this.job, String.valueOf(this.height), this.gender, this.location,
                this.email, this.appliedPath, this.recommendUserName, this.hobby.toString(),
                this.nickName, this.detailJob, this.education, String.valueOf(this.smoking), this.drinking, this.grade
        };
        return cellDatas;
    }
}
