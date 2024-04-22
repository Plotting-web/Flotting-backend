package com.flotting.api.login.service;

import com.flotting.api.login.model.LoginRequestVo;
import com.flotting.api.login.model.LoginResponseVo;
import com.flotting.api.user.model.UserSimpleRequestDto;
import com.flotting.api.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {


    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    private final static String PHONE_NUMBER = "01012341234";

    private final static String NAME = "testuser1";
    private final static Integer AGE = 22;
    private final static String JOB = "BUSNINESS";
    private final static String PASSWORD = "test123";


    @BeforeEach
    void signin() {
        UserSimpleRequestDto userSimpleRequestDto = new UserSimpleRequestDto(NAME, PASSWORD, AGE, PHONE_NUMBER, JOB);
        userService.saveSimpleUserInfo(userSimpleRequestDto);
    }

    @Test
    void userLogin() {
        //given
        LoginRequestVo loginRequestVo = new LoginRequestVo(PHONE_NUMBER, PASSWORD);

        //when
        LoginResponseVo loginResponseVo = loginService.userLogin(loginRequestVo);

        //then
        Assertions.assertThat(loginResponseVo.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }
}