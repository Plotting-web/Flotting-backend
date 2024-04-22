package com.flotting.api.user.repository;


import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.enums.UserStatusEnum;
import com.flotting.api.user.repository.querydsl.UserDetailQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long>, UserDetailQueryDsl {
    Page<UserDetailEntity> findAllByUserStatusOrderByCreatedAtDesc(Pageable pageable, UserStatusEnum userStatusEnum);

    Page<UserDetailEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
