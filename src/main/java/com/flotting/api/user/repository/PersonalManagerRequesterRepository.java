package com.flotting.api.user.repository;

import com.flotting.api.user.entity.PersonalManagerRequesterEntity;
import com.flotting.api.user.entity.UserDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalManagerRequesterRepository extends JpaRepository<PersonalManagerRequesterEntity, Long> {
    Page<PersonalManagerRequesterEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    PersonalManagerRequesterEntity findByRequester(UserDetailEntity requester);
}
