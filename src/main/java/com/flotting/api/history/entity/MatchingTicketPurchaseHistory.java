package com.flotting.api.history.entity;


import com.flotting.api.goods.entity.GoodsEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MatchingTicketPurchaseHistory extends BaseEntity {

    /**
     * 구매자 id
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSimpleEntity userSimpleEntity;

    /**
     * 사용일자
     */
    private LocalDateTime usedAt;

    /**
     * 매칭 이력 id
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "matchingTicketPurchaseHistory")
    private MatchingHistory matchingHistory;

    /**
     * 결제 상품 id
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goodsEntity;

    /**
     * 입금시각
     */
    private LocalDateTime paidAt;

    public void withdraw() {
        this.usedAt = null;
        this.matchingHistory = null;
    }

    @Builder
    public MatchingTicketPurchaseHistory (UserSimpleEntity userSimpleEntity, LocalDateTime usedAt) {
        this.userSimpleEntity = userSimpleEntity;
        this.usedAt = usedAt;
    }
}
