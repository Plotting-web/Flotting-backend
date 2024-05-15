package com.flotting.api.user.repository.querydsl.impl;


import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.QUserResponseDto;
import com.flotting.api.user.model.UserDetailResponseDto;
import com.flotting.api.user.model.UserFilterRequestDto;
import com.flotting.api.user.model.UserResponseDto;
import com.flotting.api.user.repository.querydsl.UserDetailQueryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
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
                        userDetailEntity.recommendUserName, userDetailEntity.nickName, userDetailEntity.detailJob,
                        userDetailEntity.education, userDetailEntity.smoking, userDetailEntity.drinking, userDetailEntity.grade, userDetailEntity.seq, userDetailEntity.approvedAt,
                        userDetailEntity.managerComment, userDetailEntity.mbti, userDetailEntity.preferredDate, userDetailEntity.lifeStyle, userDetailEntity.somethingWantToSay,
                        userDetailEntity.birthday, userDetailEntity.rejectedReason))
                .from(userSimpleEntity)
                .leftJoin(userSimpleEntity.userDetailEntity, userDetailEntity)
                .where(userSimpleEntity.userDetailEntity.eq(userDetailEntity)
                        .and(genderIn(filter.getGender()))
                        .and(heightBetween(filter.getHeight()))
                        .and(statusIn(filter.getUserStatus()))
                        .and(locationIn(filter.getLocation()))
                        .and(ageBetween(filter.getAge()))
                        .and(gradeIn(filter.getGrade()))
                        .and(jobIn(filter.getJob()))
                        .and(smokingEq(filter.getSmoke()))
                        .and(appliedPathIn(filter.getAppliedPath()))
                        .and(managerIdIn(filter.getManagerIds()))
                        .and(approvedAtIn(filter.getApprovedAt()))
                )
                .offset(pageable.isPaged() ? pageable.getOffset() : 0)
                .limit(pageable.isPaged() ? pageable.getPageSize() : Integer.MAX_VALUE)
                .fetch();
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDetailResponseDto> findUsersByGradeAndSimpleProfileIdNotInOrderByAgeDiffAsc(GradeEnum targetGrade, UserDetailEntity detailUser, Set<Long> exceptIds, int limit) {
        return jpaQueryFactory
                .selectFrom(userDetailEntity)
                .where(userDetailEntity.grade.eq(targetGrade)
                        .and(userDetailEntity.userSimpleEntity.userNo.notIn(exceptIds))
                        .and(userDetailEntity.gender.ne(detailUser.getGender())))
                .orderBy(userDetailEntity.birthYear.subtract(detailUser.getBirthYear()).abs().asc())
                .limit(limit)
                .fetch()
                .stream().map(UserDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailResponseDto> findUsersBySimpleProfileIdNotInOrderByAgeDiffAsc(UserDetailEntity targetUser, Set<Long> exceptIds, int limit) {
        return jpaQueryFactory
                .selectFrom(userDetailEntity)
                .where((userDetailEntity.userSimpleEntity.userNo.notIn(exceptIds))
                        .and(userDetailEntity.gender.ne(targetUser.getGender())))
                .orderBy(userDetailEntity.grade.desc(), userDetailEntity.birthYear.subtract(targetUser.getBirthYear()).abs().asc())
                .limit(limit)
                .fetch()
                .stream().map(UserDetailResponseDto::new)
                .collect(Collectors.toList());
    }


    private BooleanBuilder heightBetween(UserFilterRequestDto.IntegerScopeModel height) {
        return nullSafeBuilder(() -> userDetailEntity.height.between(height.getMin(), height.getMax()));
    }

    private BooleanBuilder ageBetween(UserFilterRequestDto.IntegerScopeModel age) {
        return nullSafeBuilder(() -> userSimpleEntity.age.between(age.getMin(), age.getMax()));
    }

    private BooleanBuilder genderIn(List<GenderEnum> genderEnum) {
        if(CollectionUtils.isEmpty(genderEnum)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.gender.in(genderEnum));
    }

    private BooleanBuilder statusIn(List<UserStatusEnum> userStatusEnums) {
        if(CollectionUtils.isEmpty(userStatusEnums)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.userStatus.in(userStatusEnums));
    }

    private BooleanBuilder locationIn(List<LocationEnum> locationEnum) {
        if(CollectionUtils.isEmpty(locationEnum)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.location.in(locationEnum));
    }

    private BooleanBuilder gradeIn(List<GradeEnum> gradeEnum) {
        if(CollectionUtils.isEmpty(gradeEnum)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.grade.in(gradeEnum));
    }

    private BooleanBuilder smokingEq(Boolean isSmoke) {
        return nullSafeBuilder(() -> userDetailEntity.smoking.eq(isSmoke));
    }

    private BooleanBuilder jobIn(List<JobEnum> jobEnum) {
        if(CollectionUtils.isEmpty(jobEnum)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.job.in(jobEnum));
    }

    private Predicate managerIdIn(List<Long> managerIds) {
        if(CollectionUtils.isEmpty(managerIds)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.manager.seq.in(managerIds));
    }

    private Predicate appliedPathIn(List<AppliedPathEnum> appliedPath) {
        if(CollectionUtils.isEmpty(appliedPath)) {
            return new BooleanBuilder();
        }
        return nullSafeBuilder(() -> userDetailEntity.appliedPath.in(appliedPath));
    }

    private Predicate approvedAtIn(UserFilterRequestDto.DateScopeModel approvedAt) {
        return nullSafeBuilder(() -> userDetailEntity.approvedAt.between(approvedAt.getMin(), approvedAt.getMax()));
    }
}
