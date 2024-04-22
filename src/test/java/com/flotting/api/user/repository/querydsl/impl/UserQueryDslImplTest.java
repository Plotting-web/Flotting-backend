package com.flotting.api.user.repository.querydsl.impl;

import com.flotting.api.user.SampleDataMaker;
import com.flotting.api.user.enums.*;
import com.flotting.api.user.model.*;
import com.flotting.api.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserQueryDslImplTest extends SampleDataMaker {

    @Autowired
    private UserService userService;

    @Test
    public void 사용자_1차프로필_저장() {
        //given
        UserSimpleRequestDto requestDto = UserSimpleRequestDto.builder()
                .name("ABC")
                .job(JobEnum.BUSNINESS.name())
                .age(20)
                .phoneNumber("01043394339")
                .build();

        //when
        UserSimpleResponseDto userSimpleResponseDto = userService.saveSimpleUserInfo(requestDto);

        //then
        Assertions.assertThat(userSimpleResponseDto).isNotNull();
        Assertions.assertThat(userSimpleResponseDto.getName()).isEqualTo(requestDto.getName());
    }

    @Test
    public void 사용자_2차프로필_저장() {
        //given
        UserSimpleResponseDto userSimpleResponseDto = userService.saveSimpleUserInfo(UserSimpleRequestDto.builder()
                .name("ABC")
                .job(JobEnum.BUSNINESS.name())
                .age(20)
                .phoneNumber("01043394339")
                .build());
        UserDetailRequestDto requestDto = UserDetailRequestDto.builder()
                .height(190)
                .smoking(Boolean.FALSE)
                .detailJob("abc")
                .drinking(DrinkingEnum.ONE_WEEK.name())
                .education(EducationEnum.COLLEGE_ACADEMY_ATTENDING.name())
                .email("abc")
                .grade(GradeEnum.D.name())
                .hobby(List.of("abc"))
                .location(LocationEnum.SEOUL_EAST.name())
                .nickName("bing")
                .path(AppliedPathEnum.ETC.name())
                .gender(GenderEnum.F.name())
                .recommendUserName("abc")
                .URI("uri").build();

        //when
        UserDetailResponseDto userDetailResponseDto = userService.saveDetailUserInfo(userSimpleResponseDto.getUserNo(), requestDto);

        //then
        Assertions.assertThat(userDetailResponseDto).isNotNull();
    }

    @Test
    public void 사용자_1차프로필_조회() {
        //given
        makeUserData();

        //when
        List<UserSimpleResponseDto> SimpleUserInfos = userService.getSimpleUserInfos(Pageable.unpaged());

        //then
        Assertions.assertThat(SimpleUserInfos.size()).isEqualTo(6);
    }

    @Test
    public void 사용자_2차프로필_조회() {
        //given
        List<UserResponseDto> userResponseDtos = makeUserData();

        //when
        List<UserDetailResponseDto> detailUsers = userService.getDetailUserInfos(Pageable.unpaged(), "all");

        //then
        Assertions.assertThat(detailUsers.size()).isEqualTo(6);
    }

    @Test
    public void 사용자_등급별_조회() {
        //given
        List<UserResponseDto> userResponseDtos = makeUserData();

        //when
        List<UserDetailResponseDto> users = userService.getDetailUserInfosByGrade(GradeEnum.D.name());

        //then
        Assertions.assertThat(users.size()).isEqualTo(3);
    }

    @Test
    public void 사용자_필터링() {
        //TODO 입력안하는거는 all
        //given
        makeUserData();

        //when
        UserFilterRequestDto userFilterRequestDto = new UserFilterRequestDto();
        List<String> body = List.of(BodyEnum.NORMAL.name(), BodyEnum.RELIABLE.name());
        Pageable unpaged = Pageable.unpaged();
        List<UserResponseDto> users = userService.getUsersByFilter(userFilterRequestDto,unpaged);

        //then
        Assertions.assertThat(users.size()).isEqualTo(1);
    }
}