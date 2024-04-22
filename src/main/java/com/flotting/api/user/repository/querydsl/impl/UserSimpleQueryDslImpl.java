package com.flotting.api.user.repository.querydsl.impl;


import com.flotting.api.user.model.UserSimpleResponseDto;
import com.flotting.api.user.repository.querydsl.UserSimpleQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.flotting.api.user.entity.QUserSimpleEntity.userSimpleEntity;

@RequiredArgsConstructor
@Repository
public class UserSimpleQueryDslImpl implements UserSimpleQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional(readOnly = true)
    public List<UserSimpleResponseDto> findAllSimpleUserInfos(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(userSimpleEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().map(UserSimpleResponseDto::new)
                .collect(Collectors.toList());
    }

}
