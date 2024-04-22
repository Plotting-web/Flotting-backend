package com.flotting.api.user.repository;


import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.repository.querydsl.UserSimpleQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSimpleRepository extends JpaRepository<UserSimpleEntity, Long>, UserSimpleQueryDsl {
    Optional<UserSimpleEntity> findOneWithAuthoritiesByName(String userName);
    Optional<UserSimpleEntity> findByPhoneNumber(String phoneNumber);
}
