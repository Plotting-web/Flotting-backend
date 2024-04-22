package com.flotting.api.goods.entity;

import com.flotting.api.util.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * 상품구조
 */
@Entity
@Getter
public class GoodsEntity extends BaseEntity {

    /**
     * 상품이름
     */
    private String name;

    /**
     * 상품 가격
     */
    private Long price;


}
