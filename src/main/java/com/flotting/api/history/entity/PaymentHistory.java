package com.flotting.api.history.entity;

import com.flotting.api.goods.entity.GoodsEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 결제 이력 테이블
 */
@Entity
@Getter
@Table(name = "payment_history")
public class PaymentHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 결제일
     */
    private LocalDateTime paidAt;

    /**
     * 결제자
     */
    @ManyToOne
    @JoinColumn(name = "payer")
    private UserSimpleEntity payer;

    /**
     * 결제 매칭 id
     */
    @OneToOne
    @JoinColumn(name = "paid_matching_id")
    private MatchingHistory matchingHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goods;
}
