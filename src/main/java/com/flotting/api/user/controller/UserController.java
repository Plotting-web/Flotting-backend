package com.flotting.api.user.controller;


import com.flotting.api.user.model.*;
import com.flotting.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "user API", description = "user 조회/저장 API")
@RequestMapping("/user")
public class UserController {

    /**
     * TODO : 서버 기동 될 떄, 샘플데이터 만들기 userStatus 별로 4개정도
     * 결제이력 관리 GoodsEntity 주석 처리하곻 상품명 가격으로 관리
     * 고객정보 검색할 때 filterRequestDto도 수정
     * 결제이력관리 - 비고란 저장하는 api추가 필요
     * 결제이력관리 - 입금확인 클릭하는 api추가 필요
     * 결제이력관리 - 전체 정보 저장하는 api추가 필요
     */
    private final UserService userService;

    @Operation(summary = "filter에 해당하는 user조회", description = "1,2차 프로필 정보 조회")
    @Parameter(name = "userFilterRequestDto", description = "성별, 흡연여부, 회원상태, 등급, 거주지, 신청경로, 승인매니저id, 승인일자, 키, 나이")
    @ApiResponse(responseCode = "200", description = "user조회 성공", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @GetMapping("/filter")
    public List<UserResponseDto> getUsersByFilter( @RequestBody UserFilterRequestDto userFilterRequestDto,
                                                   @PageableDefault(size = 15) Pageable pageable) {
        return userService.getUsersByFilter( userFilterRequestDto, pageable);
    }

    @Operation(summary = "유저 회원가입", description = "pass 회원가입 로직을 적용하기 전 테스트용으로 만들어 놓은 회원가입 로직입니다.")
    @Parameter(name = "userSimpleRequestDto", description = "requestParam : 이, 패스워드 , 나이 , 휴대폰번호, 직업")
    @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = UserSimpleResponseDto.class)))
    @PostMapping("/signin")
    public UserSimpleResponseDto singin( @RequestBody UserSimpleRequestDto userSimpleRequestDto) {
        return userService.saveSimpleUserInfo( userSimpleRequestDto);
    }

    @Operation(summary = "1차 프로필 조회", description = "1차 프로필 전체 user조회")
    @ApiResponse(responseCode = "200", description = "user조회 성공", content = @Content(schema = @Schema(implementation = UserSimpleResponseDto.class)))
    @GetMapping("/simple-info/all")
    public List<UserSimpleResponseDto> getSimpleUserInfos(Pageable pageable) {
        return userService.getSimpleUserInfos(pageable);
    }

    @Operation(summary = "2차 프로필 조회", description = "2차 프로필 전체 user조회")
    @ApiResponse(responseCode = "200", description = "user조회 성공", content = @Content(schema = @Schema(implementation = UserDetailResponseDto.class)))
    @GetMapping("/detail-info")
    public List<UserDetailResponseDto> getDetailUserInfos(Pageable pageable,
                                                          @RequestParam(name = "type", defaultValue = "all") String type) {
        return userService.getDetailUserInfos(pageable, type);
    }

    @Operation(summary = "1차 프로필 특정 user조회", description = "userNo, 등록일, 이름, 나이, 전화번호, 계정상태, 직군")
    @ApiResponse(responseCode = "200", description = "user조회 성공", content = @Content(schema = @Schema(implementation = UserSimpleResponseDto.class)))
    @GetMapping("/simple-info/{userId}")
    public UserSimpleResponseDto getSimpleUserInfo(
                                               @PathVariable(name = "userId") Long userId) {
        return userService.getSimpleUserDto(userId);
    }

    @Operation(summary = "1,2차 프로필 특정 user조회", description = "1차&2차 프로필 조회")
    @Parameter(name = "userId", description = "1차 프로필 userNo")
    @ApiResponse(responseCode = "200", description = "user조회 성공", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @GetMapping("/info/{userId}")
    public UserResponseDto getUserInfo(@PathVariable(name = "userId") Long userId) {
        return userService.getUserInfo(userId);
    }

    @Operation(summary = "1차 프로필 user등록", description = "1차 프로필 이름, 나이, 전화번호, 직업 신규등록")
    @Parameter(name = "userSimpleRequestDto", description = "userSimpleRequestDto : 이름, 나이, 전화번호, 직업")
    @ApiResponse(responseCode = "200", description = "user저장 성공", content = @Content(schema = @Schema(implementation = UserSimpleResponseDto.class)))
    @PostMapping("/simple-info")
    public UserSimpleResponseDto registerSimpleUserInfo(
                                                        @RequestBody UserSimpleRequestDto userSimpleRequestDto) {
        return userService.saveSimpleUserInfo( userSimpleRequestDto);
    }

    @Operation(summary = "2차 프로필 user등록", description = "2차 프로필 등록")
    @Parameter(name = "userDetailRequestDto", description = "userDetailRequestDto : 2차 프로필 정보")
    @Parameter(name = "userId", description = "1차 프로필 userNo")
    @ApiResponse(responseCode = "200", description = "user저장 성공", content = @Content(schema = @Schema(implementation = UserDetailResponseDto.class)))
    @PostMapping("/detail-info")
    public UserResponseDto registerDetailUserInfo(
                                                        @RequestBody UserDetailRequestDto userDetailRequestDto,
                                                        @RequestParam(name = "userId") Long targetUserId) {
        return userService.saveDetailUserInfo( targetUserId, userDetailRequestDto);
    }

    @Operation(summary = "1차 프로필 user수정", description = "1차 프로필 이름, 나이, 전화번호, 직업 수정")
    @Parameter(name = "userSimpleRequestDto", description = "userSimpleRequestDto : 이름, 나이, 전화번호, 직업")
    @ApiResponse(responseCode = "200", description = "user수정 성공", content = @Content(schema = @Schema(implementation = UserSimpleResponseDto.class)))
    @PutMapping("/simple/{userId}")
    public UserSimpleResponseDto updateSimpleUserInfo(
                                                      @RequestBody UserSimpleRequestDto userSimpleRequestDto,
                                                      @PathVariable(name = "userId") Long targetUserId) {
        return userService.updateSimpleUserInfo( targetUserId, userSimpleRequestDto);
    }

    @Operation(summary = "2차 프로필 user수정", description = "성별, 체형, 신장, 휴면, 거주지, 나이, 등급, 직업, 흡연 필터링")
    @Parameter(name = "userDetailRequestDto", description = "userDetailRequestDto : 2차 프로필 정보")
    @ApiResponse(responseCode = "200", description = "user수정 성공", content = @Content(schema = @Schema(implementation = UserDetailResponseDto.class)))
    @PutMapping("/detail/{userId}")
    public UserDetailResponseDto updateDetailUserInfo(
                                                  @RequestBody UserDetailRequestDto userDetailRequestDto,
                                                  @PathVariable(name = "userId") Long targetUserId) {
        return userService.updateDetailUserInfo( targetUserId, userDetailRequestDto);
    }

    @PostMapping("/excel/download")
    public void downloadExcel(@RequestBody UserFilterRequestDto userFilterRequestDto, HttpServletResponse response) {
        userService.downloadExcel(userFilterRequestDto, response);
    }

    @GetMapping("/personal-requester/list")
    public List<PersonalRequesterResponseDto> getPersonalRequesterList(@PageableDefault(size = 15) Pageable pageable) {
        return userService.getPersonalRequesterList(pageable);
    }

    @GetMapping("/personal-requester/{userId}")
    public PersonalRequesterResponseDto getPersonalRequester(@PathVariable(name = "userId")Long requesterId) {
        return userService.getPersonalRequester(requesterId);
    }

    @Operation(summary = "userStatus 변경(탈퇴 외)", description = "탈퇴 외의 상태변경")
    @Parameter(name = "status", description = "userStatusEnum-NONE, INPROGRESS, REJECT,NORMAL, FORCED_WITHDRAWAL")
    @ApiResponse(responseCode = "200", description = "userStatus 수정 성공", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @PutMapping("/status/{userId}")
    public UserResponseDto changeUserStatus(@PathVariable("userId") Long simpleUserId,
                                                  @RequestParam("type") String status) {
        return userService.changeStatus(simpleUserId, status);
    }

    @Operation(summary = "탈퇴 api", description = "탈퇴 상태변경")
    @ApiResponse(responseCode = "200", description = "탈퇴 성공", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @PutMapping("/withdraw/{userId}")
    public ResponseEntity changeUserStatus(@PathVariable("userId") Long simpleUserId) {
        userService.withdraw(simpleUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
