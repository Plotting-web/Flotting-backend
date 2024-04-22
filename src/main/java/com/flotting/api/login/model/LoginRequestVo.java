package com.flotting.api.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestVo {
    private String phoneNumber;
    private String password;
}
