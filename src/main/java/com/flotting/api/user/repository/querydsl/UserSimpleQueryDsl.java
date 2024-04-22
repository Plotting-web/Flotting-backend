package com.flotting.api.user.repository.querydsl;

import com.flotting.api.user.model.UserSimpleResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserSimpleQueryDsl {

    List<UserSimpleResponseDto> findAllSimpleUserInfos(Pageable pageable);

}
