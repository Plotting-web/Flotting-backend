package com.flotting.api.user.enums;

import java.util.Arrays;

public enum JobEnum {

    PROFESSIONAL("전문직(회계사 등)"), MID_MAJOR_COMPANY("중견기업&대기업"), FINANCE("금융직"),
    PUBLIC_COMPANY("일반(공기업/공무원)"), EDU("교육직"), LAB("연구기술직"), MEDICAL("의료직(간호사 등)"),
    BUSNINESS("사업가"), SMALL_COMPANY("중소기업"),
    FREELANCER("일반(프리랜서)"), STUDENT("학생"),
    MAJOR_COMPANY("일반(대기업)"), MID_COMPANY("일반(중견기업)"),
    MINOR_COMPANY("일반(중소기업)"), STARTUP("일반(스타트업)"), ETC("기타");

    private String name;


    JobEnum(String name) {
        this.name = name;
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
