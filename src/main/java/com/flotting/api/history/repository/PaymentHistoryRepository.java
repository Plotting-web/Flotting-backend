package com.flotting.api.history.repository;

import com.flotting.api.goods.entity.GoodsEntity;
import com.flotting.api.history.entity.PaymentHistory;
import com.flotting.api.user.entity.UserSimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    List<PaymentHistory> findByGoods(GoodsEntity goodsEntity);
    List<PaymentHistory> findByPayer(UserSimpleEntity payer);
}
