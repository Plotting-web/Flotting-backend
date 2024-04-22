package com.flotting.api.user.enums;

public enum HobbyEnum {

    /**
     * 운동, 자기계발, 독서, 외국어, 카페, 요리, 음악, 악기연주, 산책, 반려동물, 여행, 패션, 맛집탐방, 영화/넷플릭스, 기타
     */
    EXERCISE("운동"), SELF_IMPROVEMENT("자기계발"), READING("독서"), FOREIGN_LANGUAGE("외국어"),
    CAFE("카페"), COOKING("요리"), INSTRUMENT("악기연주"), WALK("산책"),
    ANIMAL("반려동물"), TRAVEL("여행"), FASHION("패션"), FAMOUS_RESTAURANT("맛집탐방"),
    MOVIE("영화/넷플릭스"), ETC("기타");

    private String name;

    HobbyEnum(String name) {
        this.name = name;
    }

    public static HobbyEnum of(String name) {
        return HobbyEnum.valueOf(name);
    }

}
