package com.flotting.api.user.entity;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.UserDetailRequestDto;
import com.flotting.api.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @CollectionTable(name = "user_resource_uri_list",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "resource_uri")
    private List<String> profileImageURIs = new ArrayList<>();

    /**
     * 신원 검증 image uri 1
     *
     */
    @Column(length = 1000)
    private String identityVerificationURI;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetailEntity", cascade = CascadeType.ALL)
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
    private LocalDate approvedAt;

    @Builder
    public UserDetailEntity(UserDetailRequestDto requestDto) {
        this.birthday = requestDto.getBirthday();
        this.appliedPath = AppliedPathEnum.of(requestDto.getAppliedPath());
        this.detailJob = requestDto.getDetailJob();
        this.lifeStyle = requestDto.getLifeStyle();
        this.somethingWantToSay = requestDto.getSomethingWantToSay();
        this.drinking = DrinkingEnum.of(requestDto.getDrinking());
        this.education = EducationEnum.of(requestDto.getEducation());
        this.grade = GradeEnum.of(requestDto.getGrade());
        this.height = requestDto.getHeight();
        this.hobby = requestDto.getHobby().stream().map(HobbyEnum::of).collect(Collectors.toList());
        this.identityVerificationURI = requestDto.getIdentityVerificationURI();
        this.profileImageURIs = requestDto.getProfileImageURIs();
        this.location = LocationEnum.of(requestDto.getLocation());
        this.nickName = requestDto.getNickName();
        this.gender = GenderEnum.of(requestDto.getGender());
        this.smoking = requestDto.getSmoking();
        this.recommendUserName = requestDto.getRecommendUserName();
        this.approvedAt = requestDto.getApprovedAt();
        this.mbti = requestDto.getMbti();
        this.character = requestDto.getCharacter().stream().map(CharacterEnum::of).collect(Collectors.toList());
        this.preferredDate = requestDto.getPreferredDate();
    }

    public UserDetailEntity updateInfo(UserDetailRequestDto requestDto) {
        this.appliedPath = AppliedPathEnum.of(requestDto.getAppliedPath());
        this.detailJob = requestDto.getDetailJob();
        this.lifeStyle = requestDto.getLifeStyle();
        this.drinking = DrinkingEnum.of(requestDto.getDrinking());
        this.education = EducationEnum.of(requestDto.getEducation());
        this.grade = GradeEnum.of(requestDto.getGrade());
        this.height = requestDto.getHeight();
        this.hobby = requestDto.getHobby().stream().map(HobbyEnum::of).collect(Collectors.toList());
        this.identityVerificationURI = requestDto.getIdentityVerificationURI();
        this.profileImageURIs = requestDto.getProfileImageURIs();
        this.location = LocationEnum.of(requestDto.getLocation());
        this.nickName = requestDto.getNickName();
        this.gender = GenderEnum.of(requestDto.getGender());
        this.smoking = requestDto.getSmoking();
        this.recommendUserName = requestDto.getRecommendUserName();
        this.mbti = requestDto.getMbti();
        this.character = requestDto.getCharacter().stream().map(CharacterEnum::of).collect(Collectors.toList());
        this.preferredDate = requestDto.getPreferredDate();
        return this;
    }

    public UserDetailEntity approveProfile(ManagerProfileEntity manager) {
        this.userStatus = UserStatusEnum.NORMAL;
        this.manager = manager;
        return this;
    }

    public UserDetailEntity rejectProfile(ManagerProfileEntity manager, String reason) {
        this.userStatus = UserStatusEnum.INPROGRESS;
        this.rejectedReason = reason;
        this.manager = manager;
        return this;
    }

    public void setSimpleUser(UserSimpleEntity simpleUser) {
        this.userSimpleEntity = simpleUser;
    }

}
