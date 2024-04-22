package com.flotting.api.util.type;

public enum AutoRecommendProcessEnum {

    INPROGRESS("진행중"), SUCCESS("플러팅 요청"), FAIL("플러팅 미요청");

    private String value;

    AutoRecommendProcessEnum(String value) {
        this.value = value;
    }
    public static AutoRecommendProcessEnum of(String name) {
        return AutoRecommendProcessEnum.valueOf(name);
    }

}
