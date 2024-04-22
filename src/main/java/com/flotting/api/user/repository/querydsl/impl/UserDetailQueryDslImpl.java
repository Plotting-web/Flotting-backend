package com.flotting.api.user.repository.querydsl.impl;


import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.QUserResponseDto;
import com.flotting.api.user.model.UserDetailResponseDto;
import com.flotting.api.user.model.UserFilterRequestDto;
import com.flotting.api.user.model.UserResponseDto;
import com.flotting.api.user.repository.querydsl.UserDetailQueryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.flotting.api.user.entity.QUserDetailEntity.userDetailEntity;
import static com.flotting.api.user.entity.QUserSimpleEntity.userSimpleEntity;

@RequiredArgsConstructor
@Repository
public class UserDetailQueryDslImpl implements UserDetailQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional(readOnly = true)
    public List<UserDetailResponseDto> findUsersByGrade(GradeEnum grade) {
        return jpaQueryFactory
                .selectFrom(userDetailEntity)
                .where(userDetailEntity.grade.eq(grade))
                .fetch()
                .stream().map(UserDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findUsersByFilter(UserFilterRequestDto filter, Pageable pageable) {
        List<UserResponseDto> result = jpaQueryFactory
                .select(new QUserResponseDto(userSimpleEntity.userNo, userSimpleEntity.name, userSimpleEntity.age, userSimpleEntity.phoneNumber, userDetailEntity.userStatus,
                        userDetailEntity.job, userDetailEntity.height, userDetailEntity.gender, userDetailEntity.location, userSimpleEntity.email, userDetailEntity.appliedPath,
                        userDetailEntity.recommendUserName, userDetailEntity.hobby, userDetailEntity.nickName, userDetailEntity.detailJob,
                        userDetailEntity.education, userDetailEntity.smoking, userDetailEntity.drinking, userDetailEntity.grade, userDetailEntity.seq, userDetailEntity.approvedAt,
                        userDetailEntity.managerComment, userDetailEntity.mbti, userDetailEntity.character, userDetailEntity.preferredDate, userDetailEntity.lifeStyle, userDetailEntity.somethingWantToSay,
                        userDetailEntity.birthday, userDetailEntity.profileImageURIs, userDetailEntity.rejectedReason))
                .from(userSimpleEntity)
                .leftJoin(userSimpleEntity.userDetailEntity, userDetailEntity)
                .where(userSimpleEntity.userDetailEntity.eq(userDetailEntity)
                        .and(genderEq(filter.getGender()))
                        .and(heightBetween(filter.getHeight()))
                        .and(statusIn(filter.isDormant()))
                        .and(locationIn(filter.getLocation()))
                        .and(ageBetween(filter.getAge()))
                        .and(gradeIn(filter.getGrade()))
                        .and(jobIn(filter.getJob()))
                        .and(smokingEq(filter.isSmoke()))
                        .and(userDetailEntity.userStatus.eq(filter.getUserStatus())))
                .offset(pageable.isPaged() ? pageable.getOffset() : 0)
                .limit(pageable.isPaged() ? pageable.getPageSize() : Integer.MAX_VALUE)
                .fetch();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetailResponseDto> findUsersByGradeAndSimpleProfileIdNotInOrderByAgeDiffAsc(GradeEnum grade, List<Long> ids, UserSimpleEntity targetUser, int limit) {
        return jpaQueryFactory
                .selectFrom(userDetailEntity)
                .where(userDetailEntity.grade.eq(grade)
                        .and(userDetailEntity.userSimpleEntity.userNo.notIn(ids))
                        .and(userDetailEntity.gender.ne(targetUser.getUserDetailEntity().getGender())))
                .orderBy(userDetailEntity.userSimpleEntity.age.subtract(targetUser.getAge()).abs().asc())
                .limit(limit)
                .fetch()
                .stream().map(UserDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailResponseDto> findUsersBySimpleProfileIdNotInOrderByAgeDiffAsc(List<Long> ids, UserSimpleEntity targetUser, int limit) {
        return jpaQueryFactory
                .selectFrom(userDetailEntity)
                .where((userDetailEntity.userSimpleEntity.userNo.notIn(ids))
                        .and(userDetailEntity.gender.ne(targetUser.getUserDetailEntity().getGender())))
                .orderBy(userDetailEntity.grade.desc(), userDetailEntity.userSimpleEntity.age.subtract(targetUser.getAge()).abs().asc())
                .limit(limit)
                .fetch()
                .stream().map(UserDetailResponseDto::new)
                .collect(Collectors.toList());
    }


    private BooleanBuilder heightBetween(UserFilterRequestDto.ScopeModel height) {
        return nullSafeBuilder(() -> userDetailEntity.height.between(height.getMin(), height.getMin()));
    }

    private BooleanBuilder ageBetween(UserFilterRequestDto.ScopeModel age) {
        return nullSafeBuilder(() -> userSimpleEntity.age.between(age.getMin(), age.getMax()));
    }

    private BooleanBuilder genderEq(GenderEnum genderEnum) {
        return nullSafeBuilder(() -> userDetailEntity.gender.eq(genderEnum));
    }

    private BooleanBuilder genderNotEq(GenderEnum genderEnum) {
        return nullSafeBuilder(() -> userDetailEntity.gender.notIn(genderEnum));
    }

    private BooleanBuilder statusIn(boolean isDormant) {
        return nullSafeBuilder(() -> {
            if(isDormant) {
                return userDetailEntity.userStatus.eq(UserStatusEnum.DORMANT);
            } else {
                return userDetailEntity.userStatus.in(UserStatusEnum.NORMAL);
            }
        });
    }

    private BooleanBuilder locationIn(List<LocationEnum> locationEnum) {
        return nullSafeBuilder(() -> userDetailEntity.location.in(locationEnum));
    }

    private BooleanBuilder gradeIn(List<GradeEnum> gradeEnum) {
        return nullSafeBuilder(() -> userDetailEntity.grade.in(gradeEnum));
    }

    private BooleanBuilder smokingEq(boolean isSmoke) {
        return nullSafeBuilder(() -> userDetailEntity.smoking.eq(isSmoke));
    }

    private BooleanBuilder jobIn(List<JobEnum> jobEnum) {
        return nullSafeBuilder(() -> userDetailEntity.job.in(jobEnum));
    }

}
