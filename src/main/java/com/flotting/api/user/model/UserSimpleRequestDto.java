package com.flotting.api.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSimpleRequestDto {

    private String name;

    private String password;

    private Integer age;

    private String phoneNumber;

    private String job;

    @Builder
    public UserSimpleRequestDto (String name,String password, Integer age, String phoneNumber, String job) {
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.job = job;
        this.name = name;
    }
}
