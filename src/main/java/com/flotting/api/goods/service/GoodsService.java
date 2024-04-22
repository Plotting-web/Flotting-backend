package com.flotting.api.goods.service;

import com.flotting.api.goods.entity.GoodsEntity;
import com.flotting.api.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional(readOnly = true)
    public GoodsEntity getGoodsById(Long goodsId) {
        GoodsEntity goodsEntity = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품"));
        return goodsEntity;
    }
}
