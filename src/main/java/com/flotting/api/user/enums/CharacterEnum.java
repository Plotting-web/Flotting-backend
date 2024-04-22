package com.flotting.api.user.enums;

public enum CharacterEnum {

    /**
     * 외향적인, 애교있느, 유머있는, 친절한, 차분한, 긍저적인, 지적인, 4차원, 열정저인, 사려깊은, 진지한, 센스있는
     */
    EXTROVERTED("외향적인"), CUTE("애교있는"), HUMOROUS("유머있는"), KING("친절한"),
    CALM("차분한"), POSITIVE("긍정적인"), INTELLIGENT("지적인"), UNIQUE("4차원"),
    PASSIONATE("열정적인"), THOUGHTFUL("사려깊은"), SERIOUS("진지한"), SENSIBLE("센스있는");

    private String name;

    CharacterEnum(String name) {
        this.name = name;
    }

    public static CharacterEnum of(String name) {
        return CharacterEnum.valueOf(name);
    }

}
