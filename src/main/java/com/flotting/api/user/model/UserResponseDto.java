package com.flotting.api.user.model;

import com.flotting.api.manager.model.ManagerProfileDto;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.*;
import com.flotting.api.util.ExcelDownloadable;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResponseDto {
//        implements ExcelDownloadable {

    @Schema(description = "1차 프로필 userId")
    private Long userNo;

    @Schema(description = "2차 프로필 userId")
    private Long detailProfileId;

    @Schema(description = "이름", example = "PASS에서 넘겨준 정보")
    private String name;

    @Schema(description = "나이", example = "PASS에서 넘겨준 정보")
    private Integer age;

    @Schema(description = "전화번호", example = "PASS에서 넘겨준 정보")
    private String phoneNumber;

    @Schema(description = "계정상태", allowableValues = { "NONE","INPROGRESS", "REJECT",
            "WITHDRAWAL", "DORMANT", "NORMAL", "FORCED_WITHDRAWAL"})
    private String userStatus;

    @Schema(description = "직업", allowableValues = {"PROFESSIONAL", "MID_MAJOR_COMPANY", "FINANCE", "PUBLIC_COMPANY", "EDU", "LAB", "MEDICAL",
            "BUSNINESS", "SMALL_COMPANY", "FREELANCER", "STUDENT", "MAJOR_COMPANY", "MID_COMPANY", "MINOR_COMPANY", "STARTUP", "ETC"})
    private String job;

    @Schema(description = "신장", example = "187")
    private Integer height;

    @Schema(description = "성별", example = "M" , allowableValues={"F", "M"})
    private String gender;

    @Schema(description = "거주지", example = "SEOUL_NORTH", allowableValues = {"SEOUL_NORTH", "SEOUL_SOUTH", "SEOUL_WEST", "SEOUL_EAST",
            "GGYEONGGI_NORTH", "GGYEONGGI_SOUTH", "GGYEONGGI_WEST", "GGYEONGGI_EAST"
    })
    private String location;

    @Schema(description = "이메일", example = "aa@naver.com")
    private String email;

    @Schema(description = "신청 경로", example = "SMALL_CLASS_C",
            allowableValues = {"SMALL_CLASS_C", "FRIP", "WADIZ", "NAVER_CAFE", "NAVER_STORE",  "RECOMMEND", "ETC", "SNS"})
    private String appliedPath;

    @Schema(description = "추천인 이름", example = "hong")
    private String recommendUserName;

    @Schema(description = "취미", example = "EXERCISE" ,
            allowableValues = {"EXERCISE", "SELF_IMPROVEMENT", "READING", "FOREIGN_LANGUAGE",
                    "CAFE", "COOKING", "INSTRUMENT", "WALK", "ANIMAL", "TRAVEL", "FASHION", "FAMOUS_RESTAURANT",
                    "MOVIE", "ETC", "MUSIC"})
    private List<String> hobby;

    @Schema(description = "닉네임", example = "star")
    private String nickName;

    @Schema(description = "상세직", example = "SK")
    private String detailJob;

    @Schema(description = "졸업 이력", example = "HIGH_SCHOOL_GRADUATION",
            allowableValues = {"DOCTOR_INPROGRESS", "DOCTOR_COMPLETE","HIGH_SCHOOL_GRADUATION", "JUNIOR_COLLEGE_ATTENDING", "JUNIOR_COLLEGE_GRADUATION", "COLLEGE_ATTENDING", "COLLEGE_GRADUATION", "COLLEGE_ACADEMY_ATTENDING", "COLLEGE_ACADEMY_GRADUATION"})
    private String education;

    @Schema(description = "흡연 여부", example = "true")
    private Boolean smoking;

    @Schema(description = "음주 빈도", example = "THREE_WEEK",
            allowableValues = {"THREE_WEEK", "TWO_WEEK", "ONE_WEEK", "ZERO_WEEK"})
    private String drinking;

    @Schema(description =  "신원 검증 image uri", example = "file://~")
    private String identityVerificationURI;

    @Schema(description = "등급", example = "G", allowableValues = {"G", "D", "P"})
    private String grade;

    @Schema(description = "프로필 승인한 매니저", example = "342")
    private Long managerId;

    @Schema(description = "mbti", example = "isfp")
    private String mbti;

    @Schema(description = "매니저 코멘트")
    private String managerComment;

    @Schema(description = "내성격", example = "EXTROVERTED",
            allowableValues = {"EXTROVERTED", "CUTE", "HUMOROUS", "KING", "CALM", "POSITIVE", "INTELLIGENT", "UNIQUE",
                    "PASSIONATE", "THOUGHTFUL", "SERIOUS", "SENSIBLE"})
    private List<String> character;

    @Schema(description = "선호데이트", example = "산책")
    private String preferredDate;

    @Schema(description = "라이프스타일", example = "운동")
    private String lifeStyle;

    @Schema(description = "하고싶은말", example = "잘부탁드립니다")
    private String somethingWantToSay;

    @Schema(description = "생일", example = "970301")
    private String birthday;

    @Schema(description = "사진경로", example = "file://~")
    private List<String> profileImageURIs;

    @Schema(description = "거절사유")
    private String rejectedReason;

    @Schema(description = "승인일자", example = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approvedAt;
    @Schema(description = "상세주소")
    private String detailLocation;

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
        this.job = detailInfo.getJob();
    }

    @QueryProjection
    public UserResponseDto(Long userNo, String name, Integer age, String phoneNumber, UserStatusEnum userStatus,
                           Object job, Integer height, GenderEnum gender, LocationEnum location, String email,
                           AppliedPathEnum appliedPath, String recommendUserName, String nickName,
                           String detailJob, EducationEnum education, Boolean smoking, DrinkingEnum drinking,  GradeEnum grade, Long detailProfileId,
                           LocalDateTime approvedAt, String managerComment, String mbti,  String preferredDate, String lifeStyle, String somethingWantToSay,
                           String birthday, String rejectedReason) {
        this.userNo = userNo;
        this.age = age;
//        this.job= job.name();
        this.job= Objects.isNull(job) ? null : job.toString();
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
//        this.hobby = hobby.stream().map(HobbyEnum::name).collect(Collectors.toList());
        this.location = location.name();;
        this.nickName = nickName;
        this.gender = gender.name();;
        this.smoking = smoking;
        this.recommendUserName = recommendUserName;
        this.detailProfileId = detailProfileId;
        this.approvedAt = approvedAt;
        this.managerComment = managerComment;
        this.mbti = mbti;
//        this.character = character.stream().map(CharacterEnum::name).collect(Collectors.toList());;
        this.lifeStyle = lifeStyle;
        this.preferredDate = preferredDate;
        this.somethingWantToSay = somethingWantToSay;
        this.birthday = birthday;
        this.profileImageURIs = profileImageURIs;
        this.rejectedReason = rejectedReason;
    }

    public UserResponseDto(UserSimpleEntity userSimpleEntity, UserDetailEntity userDetailEntity) {
        this.userNo = userSimpleEntity.getUserNo();
        this.age = userSimpleEntity.getAge();
        this.phoneNumber = userSimpleEntity.getPhoneNumber();
        this.name = userSimpleEntity.getName();
        this.email = userSimpleEntity.getEmail();
        if(Objects.nonNull(userDetailEntity)) {
            this.detailProfileId = userDetailEntity.getSeq();
            this.appliedPath = Objects.nonNull(userDetailEntity.getAppliedPath()) ? userDetailEntity.getAppliedPath().name() : null;
            this.detailJob = userDetailEntity.getDetailJob();
            this.drinking = Objects.nonNull(userDetailEntity.getDrinking()) ? userDetailEntity.getDrinking().name() : null;
            this.education = Objects.nonNull(userDetailEntity.getEducation()) ? userDetailEntity.getEducation().name() : null;
            this.grade = Objects.nonNull(userDetailEntity.getGrade()) ? userDetailEntity.getGrade().name() : null;
            this.height = userDetailEntity.getHeight();
            this.hobby = userDetailEntity.getHobby().stream().map(HobbyEnum::name).collect(Collectors.toList());
            this.identityVerificationURI = userDetailEntity.getIdentityVerificationURI();
            this.location = Objects.nonNull(userDetailEntity.getLocation()) ? userDetailEntity.getLocation().name() : null;
            this.nickName = userDetailEntity.getNickName();
            this.gender = Objects.nonNull(userDetailEntity.getGender()) ? userDetailEntity.getGender().name() : null;
            this.smoking = userDetailEntity.getSmoking();
            this.recommendUserName = userDetailEntity.getRecommendUserName();
            this.managerId = Objects.nonNull(userDetailEntity.getManager()) ? userDetailEntity.getManager().getSeq() : null;
            this.approvedAt = userDetailEntity.getApprovedAt();
            this.mbti = userDetailEntity.getMbti();
            this.character = Objects.nonNull(userDetailEntity.getCharacter()) ? userDetailEntity.getCharacter().stream().map(CharacterEnum::name).collect(Collectors.toList()) : null;
            this.preferredDate = userDetailEntity.getPreferredDate();
            this.birthday = userDetailEntity.getBirthday();
            this.managerComment = userDetailEntity.getManagerComment();
            this.preferredDate= userDetailEntity.getPreferredDate();
            this.lifeStyle = userDetailEntity.getLifeStyle();
            this.somethingWantToSay = userDetailEntity.getSomethingWantToSay();
            this.rejectedReason = userDetailEntity.getRejectedReason();
            this.userStatus = Objects.nonNull(userDetailEntity.getUserStatus()) ? userDetailEntity.getUserStatus().name() : null;
            this.job = Objects.nonNull(userDetailEntity.getJob()) ? userDetailEntity.getJob().name() : null;
        }
    }


    //Excel 다운로드 기
//    @Override
//    public String[] getHeaders() {
//        String[] headers = {"이름", "나이", "전화번호", "계정상태", "직업", "신장", "성별", "거주지", "이메일", "신청 경로", "추천인 이름",
//                "선호도1위", "선호 구체적 설명", "나의 매력", "나의 연애관", "취미", "닉네임", "체형", "직장명", "졸업 이력", "흡연 여부", "음주 빈도", "등급"};
//        return headers;
//    }
//ho
//    @Override
//    public String[] getCellDatas() {
//        String[] cellDatas = {
//                this.name, String.valueOf(this.age), this.phoneNumber, this.userStatus, this.job, String.valueOf(this.height), this.gender, this.location,
//                this.email, this.appliedPath, this.recommendUserName, Objects.nonNull(this.hobby) ? this.hobby.toString() : null,
//                this.nickName, this.detailJob, this.education, String.valueOf(this.smoking), this.drinking, this.grade
//        };
//        return cellDatas;
//    }
}
