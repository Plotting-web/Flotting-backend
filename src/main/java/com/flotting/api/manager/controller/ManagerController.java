package com.flotting.api.manager.controller;

import com.flotting.api.manager.model.ApproveRequestDto;
import com.flotting.api.manager.model.RejectRequestDto;
import com.flotting.api.manager.service.ManagerService;
import com.flotting.api.user.model.UserDetailResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "manager API", description = "manager 조회/저장 API")
public class ManagerController {

    private final ManagerService managerService;
//
//    @PostConstruct
//    public void makeSampleData() {
//        managerService.makeSampleData();
//    }

    @Operation(summary = "매니저 프로필 승인/반려", description = "1,2차 프로필 매니저 승인/반려")
    @Parameter(name = "userId", description = "1차 프로필 userNo")
    @Parameter(name = "approveRequestDto", description = "승인한 managerId, 매니저 코멘트, 등급")
    @PutMapping("/approve/user/{userId}")
    public ResponseEntity approveUserProfile(@PathVariable(name = "userId") Long userId,
                                             @RequestBody(required = true) ApproveRequestDto approveRequestDto) {
        managerService.approveInfo(userId, approveRequestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "매니저 프로필 승인/반려", description = "1,2차 프로필 매니저 승인/반려")
    @Parameter(name = "userId", description = "userDetailRequestDto : 2차 프로필 정보")
    @ApiResponse(responseCode = "200", description = "user수정 성공", content = @Content(schema = @Schema(implementation = UserDetailResponseDto.class)))
    @PutMapping("/reject/user/{userId}")
    public ResponseEntity rejectUserProfile(
            @PathVariable(name = "userId") Long detailEntityId,
            @RequestBody(required = true) RejectRequestDto requestDto) {
        managerService.rejectInfo(detailEntityId, requestDto);
        return ResponseEntity.ok().build();
    }
}
