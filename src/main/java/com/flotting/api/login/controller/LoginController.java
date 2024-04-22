package com.flotting.api.login.controller;

import com.flotting.api.login.model.LoginRequestVo;
import com.flotting.api.login.model.LoginResponseVo;
import com.flotting.api.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @CrossOrigin("*")
    @Operation(summary = "사용자 로그인", description = "로그인")
    @Parameter(name = "LoginRequestVo", description = "requestParam : 아이디, 패스워드")
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginResponseVo.class)))
    @PostMapping("/user/login")
    private LoginResponseVo login(@RequestBody LoginRequestVo loginRequestVo) {
        return loginService.userLogin(loginRequestVo);
    }
}
