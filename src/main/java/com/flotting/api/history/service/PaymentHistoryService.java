package com.flotting.api.history.service;

import com.flotting.api.goods.entity.GoodsEntity;
import com.flotting.api.goods.service.GoodsService;
import com.flotting.api.history.entity.PaymentHistory;
import com.flotting.api.history.model.PaymentHistoryResponseDto;
import com.flotting.api.history.model.PaymentResponseDto;
import com.flotting.api.history.repository.PaymentHistoryRepository;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserService userService;
    private final GoodsService goodsService;

    @Transactional(readOnly = true)
    public List<PaymentHistoryResponseDto> getAllPaymentHistory(Pageable pageable) {
        ArrayList<PaymentHistoryResponseDto> result = new ArrayList<>();
        List<PaymentHistory> datas = paymentHistoryRepository.findAll(pageable).getContent();
        datas.forEach(data -> {
            result.add(new PaymentHistoryResponseDto(data));
        });
        return result;
    }

    @Transactional(readOnly = true)
    public PaymentResponseDto getPaymentThroughGoodsId(Long goodsId) {
        GoodsEntity goods = goodsService.getGoodsById(goodsId);
        List<PaymentHistory> data = paymentHistoryRepository.findByGoods(goods);
        PaymentResponseDto result = new PaymentResponseDto(data);
        return result;
    }

    @Transactional(readOnly = true)
    public PaymentResponseDto getPaymentThroughPayer(Long payerId) {
        UserSimpleEntity payer = userService.getSimpleUser(payerId);
        List<PaymentHistory> data = paymentHistoryRepository.findByPayer(payer);
        PaymentResponseDto result = new PaymentResponseDto(data);
        return result;
    }
}
