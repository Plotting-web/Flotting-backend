package com.flotting.api.user.enums;

import java.util.Arrays;

public enum EducationEnum {

    HIGH_SCHOOL_GRADUATION("고등학교 졸업"), JUNIOR_COLLEGE_ATTENDING("전문대 대학"), JUNIOR_COLLEGE_GRADUATION("전문대 졸업"),
    COLLEGE_ATTENDING("대햑교 재학"), COLLEGE_GRADUATION("대학교 졸업"), COLLEGE_ACADEMY_ATTENDING("대학원 재학"),
    COLLEGE_ACADEMY_GRADUATION("대학원 졸업"), DOCTOR_INPROGRESS("박사과정 진행"), DOCTOR_COMPLETE("박사과정 수료");

    private String value;

    EducationEnum(String value) {
        this.value = value;
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
