package com.flotting.api.user.enums;

import java.util.Arrays;

public enum DrinkingEnum {

    THREE_WEEK("자주 마심"),TWO_WEEK("가끔 마심"),
    ONE_WEEK("거의 안 마심"), ZERO_WEEK("아예 안 마심");

    private String value;

    DrinkingEnum(String value) {
        this.value = value;
    }
    public static DrinkingEnum of(String name) {
        return DrinkingEnum.valueOf(name);
    }

    public static DrinkingEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.value.equals(data))
                .findAny()
                .orElse(null);
    }
}
