package com.flotting.api.user.enums;

public enum BodyEnum {

    MUSCULAR("근육질", 3, 2), SOLID("탄탄",3, 2),
    NORMAL("평균", 2, 3),
    SLIM("슬림", 1, 3), RELIABLE("듬직", 1, 0),
    VOLUME("볼륨", 0, 3), CHUBBY("통통", 0, 0) ;

    private String name;
    private int maleScore;
    private int femaleScore;

    BodyEnum(String name, int maleScore, int femaleScore) {
        this.name = name;
        this.maleScore = maleScore;
        this.femaleScore = femaleScore;
    }

    public int getScore(GenderEnum gender) {
        if(GenderEnum.M.equals(gender)) {
            return this.maleScore;
        } else {
            return this.femaleScore;
        }
    }

    public static BodyEnum of(String name) {
        return BodyEnum.valueOf(name);
    }
}
