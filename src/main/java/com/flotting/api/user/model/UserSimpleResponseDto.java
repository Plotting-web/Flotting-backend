package com.flotting.api.user.model;

import com.flotting.api.user.entity.UserSimpleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor
@Builder
public class UserSimpleResponseDto {

    private Long userNo;

    /**
     * 생성일
     */
    private LocalDateTime createdAt;

    /**
     * 이름
     */
    private String name;

    /**
     * 나이
     */
    private Integer age;

    /**
     * 전화번호
     */
    private String phoneNumber;

    /**
     * 2차프로필 id
     */
    private Long userDetailEntityId;

    /**
     * email
     */
    private String email;

    public UserSimpleResponseDto(UserSimpleEntity user) {
        this.userNo = user.getUserNo();
        this.createdAt = user.getCreatedAt();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userDetailEntityId = Objects.nonNull(user.getUserDetailEntity()) ? user.getUserDetailEntity().getSeq() : null;
    }
}
