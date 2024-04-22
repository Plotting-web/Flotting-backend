package com.flotting.api.login.service;


import com.flotting.api.login.model.LoginRequestVo;
import com.flotting.api.login.model.LoginResponseVo;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.service.UserService;
import com.flotting.common.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseVo userLogin(LoginRequestVo loginRequestVo) {
        UserSimpleEntity userProfileEntity = userService.getUserByPhoneNumber(loginRequestVo.getPhoneNumber());
        if (!passwordEncoder.matches(loginRequestVo.getPassword(), userProfileEntity.getPassword())) {
            throw new RuntimeException("사용자 인증 실패");
        }
        String accessToken = jwtTokenProvider.createToken(userProfileEntity.getPhoneNumber(), List.of("ROLE_USER"));
        return new LoginResponseVo(userProfileEntity, accessToken);
    }
}
