package com.flotting.api.login.model;

import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.model.UserSimpleResponseDto;
import lombok.Getter;


@Getter
public class LoginResponseVo extends UserSimpleResponseDto {
    private final String accessToken;


    public LoginResponseVo(UserSimpleEntity userSimpleEntity, String accessToken) {
        super(userSimpleEntity);
        this.accessToken = accessToken;
    }
}
