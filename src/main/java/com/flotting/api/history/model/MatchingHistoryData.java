package com.flotting.api.history.model;

import com.flotting.api.history.entity.MatchingHistory;
import com.flotting.api.util.ExcelDownloadable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MatchingHistoryData implements ExcelDownloadable {

    private String requestUserName;
    private String requestUserGrade;
    private String requestUserApprovedAt;

    private String receivedUserName;
    private String receivedUserGrade;

    public MatchingHistoryData(MatchingHistory history) {
        this.requestUserName = history.getRequester().getName();
        this.requestUserGrade = history.getRequester().getUserDetailEntity().getGrade().name();
        this.requestUserApprovedAt = history.getRequester().getUserDetailEntity().getApprovedAt().toString();
        this.receivedUserName = history.getReceiver().getName();
        this.receivedUserGrade = history.getReceiver().getUserDetailEntity().getGrade().name();
    }

    @Override
    public String[] getHeaders() {
        String[] headers = {"타겟 사용자 이름", "타겟 사용자 등급", "타겟 사용자 프로필 승인일자",
                    "자동 추천된 사용자 이름", "자동 추천된 사용자 등급"};
        return headers;
    }

    @Override
    public String[] getCellDatas() {
        String[] cellDatas = {
                this.requestUserName, this.requestUserGrade, this.requestUserApprovedAt,
                this.receivedUserName, this.receivedUserGrade
        };
        return cellDatas;
    }
}
