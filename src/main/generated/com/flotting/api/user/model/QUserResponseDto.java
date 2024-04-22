package com.flotting.api.user.model;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.flotting.api.user.model.QUserResponseDto is a Querydsl Projection type for UserResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserResponseDto extends ConstructorExpression<UserResponseDto> {

    private static final long serialVersionUID = 824311079L;

    public QUserResponseDto(com.querydsl.core.types.Expression<Long> userNo, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<com.flotting.api.user.enums.UserStatusEnum> userStatus, com.querydsl.core.types.Expression<com.flotting.api.user.enums.JobEnum> job, com.querydsl.core.types.Expression<Integer> height, com.querydsl.core.types.Expression<com.flotting.api.user.enums.GenderEnum> gender, com.querydsl.core.types.Expression<com.flotting.api.user.enums.LocationEnum> location, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<com.flotting.api.user.enums.AppliedPathEnum> appliedPath, com.querydsl.core.types.Expression<String> recommendUserName, com.querydsl.core.types.Expression<? extends java.util.List<com.flotting.api.user.enums.HobbyEnum>> hobby, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> detailJob, com.querydsl.core.types.Expression<com.flotting.api.user.enums.EducationEnum> education, com.querydsl.core.types.Expression<Boolean> smoking, com.querydsl.core.types.Expression<com.flotting.api.user.enums.DrinkingEnum> drinking, com.querydsl.core.types.Expression<com.flotting.api.user.enums.GradeEnum> grade, com.querydsl.core.types.Expression<Long> detailProfileId, com.querydsl.core.types.Expression<java.time.LocalDate> approvedAt, com.querydsl.core.types.Expression<String> managerComment, com.querydsl.core.types.Expression<String> mbti, com.querydsl.core.types.Expression<? extends java.util.List<com.flotting.api.user.enums.CharacterEnum>> character, com.querydsl.core.types.Expression<String> preferredDate, com.querydsl.core.types.Expression<String> lifeStyle, com.querydsl.core.types.Expression<String> somethingWantToSay, com.querydsl.core.types.Expression<String> birthday, com.querydsl.core.types.Expression<? extends java.util.List<String>> profileImageURIs, com.querydsl.core.types.Expression<String> rejectedReason) {
        super(UserResponseDto.class, new Class<?>[]{long.class, String.class, int.class, String.class, com.flotting.api.user.enums.UserStatusEnum.class, com.flotting.api.user.enums.JobEnum.class, int.class, com.flotting.api.user.enums.GenderEnum.class, com.flotting.api.user.enums.LocationEnum.class, String.class, com.flotting.api.user.enums.AppliedPathEnum.class, String.class, java.util.List.class, String.class, String.class, com.flotting.api.user.enums.EducationEnum.class, boolean.class, com.flotting.api.user.enums.DrinkingEnum.class, com.flotting.api.user.enums.GradeEnum.class, long.class, java.time.LocalDate.class, String.class, String.class, java.util.List.class, String.class, String.class, String.class, String.class, java.util.List.class, String.class}, userNo, name, age, phoneNumber, userStatus, job, height, gender, location, email, appliedPath, recommendUserName, hobby, nickName, detailJob, education, smoking, drinking, grade, detailProfileId, approvedAt, managerComment, mbti, character, preferredDate, lifeStyle, somethingWantToSay, birthday, profileImageURIs, rejectedReason);
    }

}

