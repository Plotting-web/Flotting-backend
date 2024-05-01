package com.flotting.api.user.model;

import com.flotting.api.manager.model.ManagerProfileDto;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.enums.CharacterEnum;
import com.flotting.api.user.enums.HobbyEnum;
import com.flotting.api.user.enums.JobEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
@Schema(description = "2차 프로필 사용자 정보")
@ToString
public class UserDetailResponseDto {

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
            allowableValues = {"SMALL_CLASS_C", "FRIP", "WADIZ", "NAVER", "RECOMMEND", "ETC", "SNS"})
    private String appliedPath;

    @Schema(description = "추천인 이름", example = "hong")
    private String recommendUserName;

    @Schema(description = "취미", example = "[\"EXERCISE\"]" ,
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

    @Schema(description =  "신원 검증 image uri", example = "xcvbnm")
    private String identityVerificationURI;

    @Schema(description = "mbti", example = "isfp")
    private String mbti;

    @Schema(description = "내성격", example = "[\"EXTROVERTED\"]",
            allowableValues = {"EXTROVERTED", "CUTE", "HUMOROUS", "KING", "CALM", "POSITIVE", "INTELLIGENT", "UNIQUE",
                    "PASSIONATE", "THOUGHTFUL", "SERIOUS", "SENSIBLE"})
    private List<String> character;

    @Schema(description = "선호데이트", example = "산책")
    private String preferredDate;

    @Schema(description = "직업", example = "PROFESSIONAL",
            allowableValues = {"PROFESSIONAL", "MID_MAJOR_COMPANY", "FINANCE", "PUBLIC_COMPANY", "EDU", "LAB", "MEDICAL",
                    "BUSNINESS", "SMALL_COMPANY", "FREELANCER", "STUDENT", "MAJOR_COMPANY", "MID_COMPANY", "MINOR_COMPANY", "STARTUP", "ETC"})
    private String job;

    @Schema(description = "라이프스타일", example = "운동")
    private String lifeStyle;

    @Schema(description = "하고싶은말", example = "잘부탁드립니다")
    private String somethingWantToSay;

    @Schema(description = "생일", example = "970301")
    private String birthday;

    @Schema(description = "사진경로", example = "[\"asdfgh\"]")
    private List<String> profileImageURIs;

    @Schema(description = "계정상태", example = "INPROGRESS",
            allowableValues = { "NONE","INPROGRESS", "REJECT",
                    "WITHDRAWAL", "DORMANT", "NORMAL", "FORCED_WITHDRAWAL"})
    private String userStatus;

    @Schema(description = "id")
    private Long seq;

    @Schema(description = "등급", allowableValues = {"G", "D", "P"})
    private String grade;

    @Schema(description = "프로필 승인한 매니저")
    private ManagerProfileDto manager;

    @Schema(description = "프로필 승인일자")
    private LocalDateTime approvedAt;

    @Schema(description = "매니저 코멘트")
    private String managerComment;

    @Schema(description = "거절사유")
    private String rejectedReason;

    public UserDetailResponseDto(UserDetailEntity user) {
        this.seq = user.getSeq();
        this.appliedPath = user.getAppliedPath().name();
        this.detailJob = user.getDetailJob();
        this.drinking = user.getDrinking().name();
        this.education = user.getEducation().name();
        this.grade = Objects.nonNull(user.getGrade()) ? user.getGrade().name() : null;
        this.height = user.getHeight();
        this.hobby = user.getHobby().stream().map(HobbyEnum::name).collect(Collectors.toList());
        this.identityVerificationURI = user.getIdentityVerificationURI();
        this.location = user.getLocation().name();
        this.nickName = user.getNickName();
        this.gender = user.getGender().name();
        this.smoking = user.getSmoking();
        this.recommendUserName = user.getRecommendUserName();
        this.manager = Objects.nonNull(user.getManager()) ? new ManagerProfileDto(user.getManager()) : null;
        this.approvedAt = user.getApprovedAt();
        this.mbti = user.getMbti();
        this.character = user.getCharacter().stream().map(CharacterEnum::name).collect(Collectors.toList());
        this.preferredDate = user.getPreferredDate();
        this.birthday = user.getBirthday();
        this.managerComment = user.getManagerComment();
        this.preferredDate= user.getPreferredDate();
        this.lifeStyle = user.getLifeStyle();
        this.somethingWantToSay = user.getSomethingWantToSay();
        this.rejectedReason = user.getRejectedReason();
        this.userStatus = user.getUserStatus().name();
        this.job = user.getJob().name();
    }

    @Override
    public boolean equals(Object object) {
        if(this == null || object == null) {
            return false;
        }
        if(!(object instanceof UserDetailResponseDto)) {
            return false;
        }
        if(this.seq == ((UserDetailResponseDto) object).getSeq()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seq);
    }
}
