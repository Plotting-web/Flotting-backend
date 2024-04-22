package com.flotting.api.user.enums;

public enum GenderEnum {

    M("남자"), F("여자");

    private String value;

    GenderEnum(String value) {
        this.value =value;
    }

    public static GenderEnum of(String gender) {
        return GenderEnum.valueOf(gender);
    }
}
