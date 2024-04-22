package com.flotting.api.user.enums;

import java.util.Arrays;

public enum AppliedPathEnum {

    SMALL_CLASS_C("소모임"), FRIP("프립"),
    WADIZ("와디즈"), NAVER_STORE("네이버 스토어"), RECOMMEND("지인 추천"),
    ETC("기타");

    private String value;

    AppliedPathEnum(String value) {
        this.value =value;
    }
    public static AppliedPathEnum of(String name) {
        return AppliedPathEnum.valueOf(name);
    }

    public static AppliedPathEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.value.equals(data))
                .findAny()
                .orElse(null);
    }
}
