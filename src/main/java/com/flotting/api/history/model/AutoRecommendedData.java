package com.flotting.api.history.model;

import com.flotting.api.history.entity.AutoRecommendHistory;
import com.flotting.api.util.ExcelDownloadable;
import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Data
public class AutoRecommendedData implements ExcelDownloadable {

    private String targetUserName;
    private String targetUserGrade;
    private String targetUserAge;
    private String targetUserApprovedAt;

    private String recommendedUserName;
    private String recommendedUserGrade;
    private String recommendedUserAge;

    public AutoRecommendedData (AutoRecommendHistory history) {
        this.targetUserName = history.getReceiver().getName();
        this.targetUserGrade = history.getReceiver().getUserDetailEntity().getGrade().name();
        this.targetUserAge = history.getReceiver().getUserDetailEntity().getBirthYear().toString();
        this.targetUserApprovedAt = history.getReceiver().getUserDetailEntity().getApprovedAt().toString();
        this.recommendedUserName = history.getProfile().getName();
        this.recommendedUserGrade = history.getProfile().getUserDetailEntity().getGrade().name();
        this.recommendedUserAge = history.getProfile().getUserDetailEntity().getBirthYear().toString();
    }

    @Override
    public String[] getHeaders() {
        String[] headers = {"타겟 사용자 이름", "타겟 사용자 등급", "타겟 사용자 나이", "타겟 사용자 프로필 승인일자",
                    "자동 추천된 사용자 이름", "자동 추천된 사용자 등급","자동 추천된 사용자 나이"};
        return headers;
    }

    @Override
    public String[] getCellDatas() {
        String[] cellDatas = {
                this.targetUserName, this.targetUserGrade, this.targetUserAge, this.targetUserApprovedAt,
                this.recommendedUserName, this.recommendedUserGrade, this.recommendedUserAge
        };
        return cellDatas;
    }

    public static AutoRecommendedData emptyData(String targetUserName, int originalResultSize) {
        AutoRecommendedData autoRecommendedData = new AutoRecommendedData();
        autoRecommendedData.setTargetUserName(targetUserName);
        autoRecommendedData.setRecommendedUserName(originalResultSize + "명 미만으로 관리자에게 문의 필요");
        return autoRecommendedData;
    }
}
