package com.flotting.api.user.enums;

import java.util.Arrays;

public enum JobEnum {

    PROFESSIONAL("전문직(회계사 등)", 3, 3), MID_MAJOR_COMPANY("중견기업&대기업", 3, 1), FINANCE("금융직", 3, 2),
    PUBLIC_COMPANY("일반(공기업/공무원)", 2, 3), EDU("교육직", 2, 3), LAB("연구기술직", 2, 2), MEDICAL("의료직(간호사 등)",2, 2),
    BUSNINESS("사업가", 1, 1), SMALL_COMPANY("중소기업", 1, 1),
    FREELANCER("일반(프리랜서)", 0, 1), STUDENT("학생", 0, 2),
    MAJOR_COMPANY("일반(대기업)", 3, 3), MID_COMPANY("일반(중견기업)", 3, 3),
    MINOR_COMPANY("일반(중소기업)", 3, 3), STARTUP("일반(스타트업)", 3, 3);

    private String name;
    private int maleScore;
    private int femaleScore;


    JobEnum(String name, int maleScore, int femaleScore) {
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

    public static JobEnum of(String name) {
        return JobEnum.valueOf(name);
    }

    public static JobEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(data))
                .findAny()
                .orElse(null);
    }
}
