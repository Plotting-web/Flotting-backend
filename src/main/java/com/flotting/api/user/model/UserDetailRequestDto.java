package com.flotting.api.user.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRequestDto {

    private Integer height;

    private String birthday;

    private String gender;

    private String location;

    private String email;

    private String appliedPath;

    private String recommendUserName;

    private String lifeStyle;

    private String somethingWantToSay;

    private List<String>  hobby;

    private String nickName;

    private String job;

    private String detailJob;

    private String education;

    private Boolean smoking;

    private String drinking;

    private String mbti;

    private List<String> character;

    private String preferredDate;

    private String identityVerificationURI;

    private List<String> profileImageURIs;

    private String grade;

    private Long managerId;

    private LocalDate approvedAt;

    private String userStatus;

    private String rejectedReason;

    @Builder
    public UserDetailRequestDto(String path, String detailJob, String lifeStyle, String somethingWantToSay,
                                String drinking, String education, String email, String grade,
                                Integer height, List<String> hobby, String URI, String location,
                                List<String> character, String nickName, String preferredDate, List<String> profileImageURIs,
                                String gender, Boolean smoking, String recommendUserName,
                                String mbti, LocalDate approvedAt, String userStatus) {
        this.appliedPath = path;
        this.detailJob = detailJob;
        this.lifeStyle = lifeStyle;
        this.somethingWantToSay = somethingWantToSay;
        this.drinking = drinking;
        this.education =  education;
        this.email = email;
        this.grade = grade;
        this.height = height;
        this.hobby = hobby;
        this.identityVerificationURI = URI;
        this.location = location;
        this.character = character;
        this.nickName = nickName;
        this.gender = gender;
        this.smoking = smoking;
        this.recommendUserName = recommendUserName;
        this.approvedAt = approvedAt;
        this.mbti = mbti;
        this.userStatus = userStatus;
        this.preferredDate = preferredDate;
        this.profileImageURIs = profileImageURIs;
    }
}
