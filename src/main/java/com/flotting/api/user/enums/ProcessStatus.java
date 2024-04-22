package com.flotting.api.user.enums;

public enum ProcessStatus {

    WAIT("대기"), HOLD("보류"), PROCEEDING("처리중"), COMPLETE("완");

    private String name;

    ProcessStatus(String name) {
        this.name = name;
    }
    public static ProcessStatus of(String name) {
        return ProcessStatus.valueOf(name);
    }

}
