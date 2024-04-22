package com.flotting.api.user.repository.querydsl;

import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.GradeEnum;
import com.flotting.api.user.model.UserDetailResponseDto;
import com.flotting.api.user.model.UserFilterRequestDto;
import com.flotting.api.user.model.UserResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Supplier;

public interface UserDetailQueryDsl {

    List<UserDetailResponseDto> findUsersByGrade(GradeEnum grade);

    List<UserResponseDto> findUsersByFilter(UserFilterRequestDto filter, Pageable pageable);

    List<UserDetailResponseDto> findUsersByGradeAndSimpleProfileIdNotInOrderByAgeDiffAsc(GradeEnum gradeEnum, List<Long> ids, UserSimpleEntity targetUser, int limit);
    List<UserDetailResponseDto> findUsersBySimpleProfileIdNotInOrderByAgeDiffAsc(List<Long> ids, UserSimpleEntity targetUser, int limit);

    default BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException | NullPointerException e) {
            return new BooleanBuilder();
        }
    }
}
