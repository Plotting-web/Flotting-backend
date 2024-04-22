package com.flotting.api.history.controller;

import com.flotting.api.history.model.PaymentHistoryResponseDto;
import com.flotting.api.history.model.PaymentResponseDto;
import com.flotting.api.history.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    /**
     * 결제 이력 테이블에 있는 요약 값 노출
     * @return
     */
    @GetMapping("/payment-history/all")
    public List<PaymentHistoryResponseDto> getAllHistories(Pageable pageable) {
        return paymentHistoryService.getAllPaymentHistory(pageable);
    }

    /**
     * 상품 별 판매 개수 / 금액
     */
    @GetMapping("/payment/{goodsId}")
    public PaymentResponseDto getPaymentThroughGoodsId(@PathVariable(name = "goodsId") Long goodsId) {
        return paymentHistoryService.getPaymentThroughGoodsId(goodsId);
    }

    /**
     * 해당 고객의 총 구매 개수 및 금액
     */
    @GetMapping("/payment/{payerId}")
    public PaymentResponseDto getPaymentThroughPayer(@PathVariable(name = "payerId") Long payerId) {
        return paymentHistoryService.getPaymentThroughPayer(payerId);
    }
}
