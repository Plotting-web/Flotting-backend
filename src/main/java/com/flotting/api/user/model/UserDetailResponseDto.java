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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
@Schema(description = "2차 프로필 사용자 정보")
@ToString
public class UserDetailResponseDto {

    @Schema(description = "id")
    private Long seq;

    @Schema(description = "신장")
    private Integer height;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "거주지", allowableValues = {"SEOUL_NORTH", "SEOUL_SOUTH", "SEOUL_WEST", "SEOUL_EAST",
            "GGYEONGGI_NORTH", "GGYEONGGI_SOUTH", "GGYEONGGI_WEST", "GGYEONGGI_EAST"
    })
    private String location;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "신청 경로", allowableValues = {"A", "B", "C", "D", "E", "F", "G", "H", "I"})
    private String appliedPath;

    @Schema(description = "추천인 이름")
    private String recommendUserName;

    @Schema(description = "취미")
    private List<String> hobby;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "상세직")
    private String detailJob;

    @Schema(description = "졸업 이력", allowableValues = {"A", "B", "C", "D", "E", "F", "G"})
    private String education;

    @Schema(description = "흡연 여부")
    private Boolean smoking;

    @Schema(description = "음주 빈도", allowableValues = {"A", "B", "C", "D"})
    private String drinking;

    @Schema(description =  "신원 검증 image uri")
    private String identityVerificationURI;

    @Schema(description = "등급", allowableValues = {"G", "D", "P"})
    private String grade;

    @Schema(description = "프로필 승인한 매니저")
    private ManagerProfileDto manager;

    @Schema(description = "프로필 승인일자")
    private LocalDate approvedAt;

    @Schema(description = "매니저 코멘트")
    private String managerComment;

    @Schema(description = "mbti")
    private String mbti;

    @Schema(description = "내성격")
    private List<String> character;

    @Schema(description = "선호데이트")
    private String preferredDate;

    @Schema(description = "직업")
    private JobEnum job;

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

    @Schema(description = "계정상태")
    private String userStatus;

    public UserDetailResponseDto(UserDetailEntity user) {
        this.seq = user.getSeq();
        this.appliedPath = user.getAppliedPath().name();
        this.detailJob = user.getDetailJob();
        this.drinking = user.getDrinking().name();
        this.education = user.getEducation().name();
        this.grade = user.getGrade().name();
        this.height = user.getHeight();
        this.hobby = user.getHobby().stream().map(HobbyEnum::name).collect(Collectors.toList());
        this.identityVerificationURI = user.getIdentityVerificationURI();
        this.location = user.getLocation().name();
        this.nickName = user.getNickName();
        this.gender = user.getGender().name();
        this.smoking = user.getSmoking();
        this.recommendUserName = user.getRecommendUserName();
        this.manager = new ManagerProfileDto(user.getManager());
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
        this.job = user.getJob();
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
