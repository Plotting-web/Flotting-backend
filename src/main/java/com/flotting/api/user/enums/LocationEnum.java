package com.flotting.api.user.enums;

import java.util.Arrays;

public enum LocationEnum {

    //서울 북부, 서울 남부, 서울 남부, 서울 동부, 경기 서부, 경기 남부, 경기 동부, 경기 북부

    SEOUL_NORTH("서울 북부"), SEOUL_SOUTH("서울 남부"), SEOUL_WEST("서울 서부"),
    SEOUL_EAST("서울 동부"), GGYEONGGI_NORTH("경기 북부"), GGYEONGGI_SOUTH("경기 남부"),
    GGYEONGGI_WEST("경기 서부"), GGYEONGGI_EAST("경기 동부");

    private String name;

    LocationEnum(String name) {
        this.name =  name;
    }

    public static LocationEnum of(String value) {
        return LocationEnum.valueOf(value);
    }

    public static LocationEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(data))
                .findAny()
                .orElse(null);
    }
}
