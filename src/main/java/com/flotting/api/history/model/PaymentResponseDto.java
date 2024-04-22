package com.flotting.api.history.model;


import com.flotting.api.history.entity.PaymentHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentResponseDto {

    private Long totalPayment = 0L;

    private Integer totalCount = 0;

    public PaymentResponseDto(List<PaymentHistory> paymentHistories) {
        paymentHistories.forEach(paymentHistory -> {
            this.totalPayment += paymentHistory.getGoods().getPrice();
        });
        this.totalCount = paymentHistories.size();
    }

}
