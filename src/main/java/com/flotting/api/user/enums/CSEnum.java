package com.flotting.api.user.enums;

public enum CSEnum {

    NORMAL("일반질의"), PROFILE_CHANGE("프로필변경");

    private String name;

    CSEnum(String name) {
        this.name =name;
    }
    public static CSEnum of(String name) {
        return CSEnum.valueOf(name);
    }

}
