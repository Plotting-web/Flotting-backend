package com.flotting.api.util.type;

public enum MatchingProcessEnum {

    /**
     * 일에 플러팅 한거면서 && 진행중 인것 체크
     * 개인별로 플러팅 해야하는 일자(이틀) 체크해서 회수해야 하는 배치 돌도록
     * 거절되면 그 즉시 회수하도록
     * TODO 매일 배치가 돌아서 수요일에 진행중인것은 다 회수하도록 + 매칭권 +1 하도록
     */
    SUCCESS("수락"), INPROGRESS("진행중"), FAIL("거절"), DELETE("삭제");

    private String value;

    MatchingProcessEnum(String value) {
        this.value = value;
    }
    public static MatchingProcessEnum of(String name) {
        return MatchingProcessEnum.valueOf(name);
    }

}
