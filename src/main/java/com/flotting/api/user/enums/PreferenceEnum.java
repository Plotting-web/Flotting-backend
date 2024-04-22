package com.flotting.api.user.enums;

public enum PreferenceEnum {

    JOB("직업"), DISTANCE("거리"), AGE("나이"), HEIGHT("신장");

    private String value;

    PreferenceEnum(String value) {
        this.value = value;
    }
    public static PreferenceEnum of(String name) {
        return PreferenceEnum.valueOf(name);
    }

}
