package com.flotting.api.history.model;

import com.flotting.api.history.entity.AutoRecommendHistory;
import com.flotting.api.util.ExcelDownloadable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class AutoRecommendedData implements ExcelDownloadable {

    private String targetUserName;
    private String targetUserGrade;
    private String targetUserApprovedAt;

    private String recommendedUserName;
    private String recommendedUserGrade;

    public AutoRecommendedData (AutoRecommendHistory history) {
        this.targetUserName = history.getReceiver().getName();
        this.targetUserGrade = history.getReceiver().getUserDetailEntity().getGrade().name();
        this.targetUserApprovedAt = history.getReceiver().getUserDetailEntity().getApprovedAt().toString();
        this.recommendedUserName = history.getProfile().getName();
        this.recommendedUserGrade = history.getProfile().getUserDetailEntity().getGrade().name();
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
                this.targetUserName, this.targetUserGrade, this.targetUserApprovedAt,
                this.recommendedUserName, this.recommendedUserGrade
        };
        return cellDatas;
    }
}
