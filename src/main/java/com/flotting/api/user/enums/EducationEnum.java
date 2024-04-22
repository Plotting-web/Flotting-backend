package com.flotting.api.user.enums;

import java.util.Arrays;

public enum EducationEnum {

    HIGH_SCHOOL_GRADUATION("고등학교 졸업", 1, 1), JUNIOR_COLLEGE_ATTENDING("전문대 대학", 1, 2), JUNIOR_COLLEGE_GRADUATION("전문대 졸업", 2, 2),
    COLLEGE_ATTENDING("대햑교 재학",1, 3), COLLEGE_GRADUATION("대학교 졸업", 3, 3), COLLEGE_ACADEMY_ATTENDING("대학원 재학", 2, 3),
    COLLEGE_ACADEMY_GRADUATION("대학원 졸업", 3, 3);

    private String value;
    private int maleScore;
    private int femaleScore;

    EducationEnum(String value, int maleScore, int femaleScore) {
        this.value = value;
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


    public static EducationEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.value.equals(data))
                .findAny()
                .orElse(null);
    }

    public static EducationEnum of(String name) {
        return EducationEnum.valueOf(name);
    }

}
