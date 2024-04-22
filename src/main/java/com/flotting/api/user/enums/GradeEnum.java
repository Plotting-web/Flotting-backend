package com.flotting.api.user.enums;

import java.util.Arrays;

public enum GradeEnum {

    G("G"), P("P"), D("D");

    private String name;

    GradeEnum(String name) {
        this.name = name;
    }

    public static GradeEnum of(String name) {
        return GradeEnum.valueOf(name);
    }

    public static GradeEnum getUpperGrade(GradeEnum grade) {
        if(GradeEnum.G.equals(grade)) {
            return GradeEnum.P;
        } else  {
            return GradeEnum.D;
        }
    }

    public static GradeEnum byValue(String data) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(data))
                .findAny()
                .orElse(null);
    }

}
