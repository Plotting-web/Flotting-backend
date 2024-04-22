package com.flotting.api.history.model;


import com.flotting.api.history.entity.PaymentHistory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentHistoryResponseDto {


    private Long id;

    private LocalDateTime paidAt;

    private Long payerId;

    private Long matchingHistoryId;

    public PaymentHistoryResponseDto(PaymentHistory paymentHistory) {
        this.id = paymentHistory.getId();
        this.paidAt = paymentHistory.getPaidAt();
        this.payerId = paymentHistory.getPayer().getUserNo();
        this.matchingHistoryId = paymentHistory.getMatchingHistory().getId();
    }
}
