package com.flotting.api.user.entity;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.manager.model.ApproveRequestDto;
import com.flotting.api.manager.model.RejectRequestDto;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.UserDetailRequestDto;
import com.flotting.api.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 2차 프로필 테이블
 */
@Entity
@Table(name = "user_detail_profile",
        indexes = @Index(name = "gradeIndex", columnList = "grade"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserDetailEntity extends BaseEntity {


    /**
     * 신장O
     */
    private Integer height;

    /**
     * 생년월일
     */
    private String birthday;

    /**
     * 성별
     */
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    /**
     * 주소
     */
    @Enumerated(value = EnumType.STRING)
    private LocationEnum location;

    /**
     * 신청경로
     */
    @Enumerated(value = EnumType.STRING)
    private AppliedPathEnum appliedPath;

    /**
     * 추천인
     */
    private String recommendUserName;

    /**
     * 내가 하는 일 혹은 나의 라이프 스타일
     */
    private String lifeStyle;

    /**
     * 미래 연인에게 하고 싶은 말
     */
    private String somethingWantToSay;

    /**
     * 나의 취미
     */
    @ElementCollection
    @CollectionTable(name = "user_hobby_list",
        joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "hobby")
    @Enumerated(value = EnumType.STRING)
    private List<HobbyEnum> hobby = new ArrayList<>();

    /**
     * 닉네임
     */
    private String nickName;

    /**
     * 공무원&공기업, 중견기업&대기업, 전문직, 사업가
     */
    private JobEnum job;

    /**
     * 상세 직업
     */
    private String detailJob;

    /**
     * 학력
     */
    @Enumerated(value = EnumType.STRING)
    private EducationEnum education;

    /**
     * 흡연 여부
     */
    private Boolean smoking;

    /**
     * 음주
     */
    @Enumerated(value = EnumType.STRING)
    private DrinkingEnum drinking;

    /**
     * mbti
     */
    private String mbti;

    /**
     * 나의 성격
     */
    @ElementCollection
    @CollectionTable(name = "user_character_list",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private List<CharacterEnum> character;

    /**
     * 선호 데이트
     */
    private String preferredDate;

    /**
     * 프로필등록 image uri(1~3장)
     * , 로 구
     */
    @ElementCollection
    @CollectionTable(name = "user_resource_url_list",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "resource_url")
    private List<String> profileImageURLs = new ArrayList<>();

    /**
     * 신원 검증 image uri 1
     *
     */
    @Column(length = 1000)
    private String identityVerificationURL;

    /**
     * 프로필 등급
     */
    private GradeEnum grade;

    /**
     * 프로필 승인 매니저
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private ManagerProfileEntity manager;

    /**
     * 승인여부+계정상태
     */
    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum userStatus;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetailEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserSimpleEntity userSimpleEntity;

    /**
     * 반려사유
     */
    private String rejectedReason;

    /**
     * 매니저 코멘트
     */
    private String managerComment;

    /**
     * 승인일자
     */
    private LocalDateTime approvedAt;

    /**
     * 출생연도 - 자동매칭 및 관리자페이지 > 사용자들 나이 필터를 위해 필요
     */
    private Integer birthYear;

    /**
     * email
     */
    private String email;

    /**
     * 상세주소
     */
    private String detailLocation;

    @Builder
    public UserDetailEntity(UserDetailRequestDto requestDto) {
        this.height = requestDto.getHeight();
        this.gender = GenderEnum.of(requestDto.getGender());
        this.location = LocationEnum.of(requestDto.getLocation());
        this.email = requestDto.getEmail();
        this.appliedPath = AppliedPathEnum.of(requestDto.getAppliedPath());
        this.recommendUserName = requestDto.getRecommendUserName();
        this.hobby = requestDto.getHobby().stream().map(HobbyEnum::of).collect(Collectors.toList());
        this.nickName = requestDto.getNickName();
        this.detailJob = requestDto.getDetailJob();
        this.education = EducationEnum.of(requestDto.getEducation());
        this.smoking = requestDto.getSmoking();
        this.drinking = DrinkingEnum.of(requestDto.getDrinking());
        this.identityVerificationURL = requestDto.getIdentityVerificationURL();
        this.mbti = requestDto.getMbti();
        this.character = requestDto.getCharacter().stream().map(CharacterEnum::of).collect(Collectors.toList());
        this.preferredDate = requestDto.getPreferredDate();
        this.job = JobEnum.of(requestDto.getJob());
        this.lifeStyle = requestDto.getLifeStyle();
        this.somethingWantToSay = requestDto.getSomethingWantToSay();
        this.birthday = requestDto.getBirthday();
        this.profileImageURLs = requestDto.getProfileImageURLs();
        this.userStatus = Objects.nonNull(requestDto.getUserStatus()) ? UserStatusEnum.of(requestDto.getUserStatus()) : UserStatusEnum.INPROGRESS;
        this.detailLocation = requestDto.getDetailLocation();
        this.birthYear = getBirthYear(requestDto.getBirthday());
        this.userStatus = UserStatusEnum.INPROGRESS;
    }

    public UserDetailEntity updateInfo(UserDetailRequestDto requestDto) {
        this.height = requestDto.getHeight();
        this.gender = GenderEnum.of(requestDto.getGender());
        this.location = LocationEnum.of(requestDto.getLocation());
        this.email = requestDto.getEmail();
        this.appliedPath = AppliedPathEnum.of(requestDto.getAppliedPath());
        this.recommendUserName = requestDto.getRecommendUserName();
        this.hobby = requestDto.getHobby().stream().map(HobbyEnum::of).collect(Collectors.toList());
        this.nickName = requestDto.getNickName();
        this.detailJob = requestDto.getDetailJob();
        this.education = EducationEnum.of(requestDto.getEducation());
        this.smoking = requestDto.getSmoking();
        this.drinking = DrinkingEnum.of(requestDto.getDrinking());
        this.identityVerificationURL = requestDto.getIdentityVerificationURL();
        this.mbti = requestDto.getMbti();
        this.character = requestDto.getCharacter().stream().map(CharacterEnum::of).collect(Collectors.toList());
        this.preferredDate = requestDto.getPreferredDate();
        this.job = JobEnum.of(requestDto.getJob());
        this.lifeStyle = requestDto.getLifeStyle();
        this.somethingWantToSay = requestDto.getSomethingWantToSay();
        this.birthday = requestDto.getBirthday();
        this.profileImageURLs = requestDto.getProfileImageURLs();
        this.detailLocation = requestDto.getDetailLocation();
        this.birthYear = getBirthYear(requestDto.getBirthday());
        this.userStatus = Objects.nonNull(requestDto.getUserStatus()) ? UserStatusEnum.of(requestDto.getUserStatus()) : UserStatusEnum.INPROGRESS;
        return this;
    }

    public UserDetailEntity updateImageUrl(List<String> imageUrl) {
        this.profileImageURLs.clear();
        this.profileImageURLs.addAll(imageUrl);
        return this;
    }

    public UserDetailEntity approveProfile(ApproveRequestDto approveRequestDto, ManagerProfileEntity manager) {
        this.userStatus = UserStatusEnum.NORMAL;
        this.grade = GradeEnum.of(approveRequestDto.getGrade());
        this.managerComment = approveRequestDto.getComment();
        this.manager = manager;
        this.approvedAt = LocalDateTime.now();
        return this;
    }

    public UserDetailEntity rejectProfile(RejectRequestDto rejectRequestDto, ManagerProfileEntity manager) {
        this.userStatus = UserStatusEnum.INPROGRESS;
        this.rejectedReason = rejectRequestDto.getReason();
        this.manager = manager;
        return this;
    }

    public void setSimpleUser(UserSimpleEntity simpleUser) {
        this.userSimpleEntity = simpleUser;
    }

    public UserDetailEntity(UserStatusEnum statusEnum) {
        this.userStatus = statusEnum;
    }

    private Integer getBirthYear(String birthday) {
        if(StringUtils.isEmpty(birthday)) {
            return  null;
        }

        Integer year = Integer.parseInt(birthday.substring(0, 1));
        if(year >= 8) {
            //80, 90년대 생
            return Integer.parseInt("19".concat(birthday.substring(0, 2)));
        } else {
            return Integer.parseInt("20".concat(birthday.substring(0, 2)));
        }
    }

    public void changeStatus(UserStatusEnum statusEnum) {
        this.userStatus = statusEnum ;
    }

    public void updateApprovedAt(LocalDateTime dateTime) {
        this.approvedAt = dateTime;
    }
}
