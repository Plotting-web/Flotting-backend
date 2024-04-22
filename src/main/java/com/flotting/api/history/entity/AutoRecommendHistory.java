package com.flotting.api.history.entity;

import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.util.BaseEntity;
import com.flotting.api.util.type.AutoRecommendProcessEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 자동 추천 이력 테이블
 */
@Entity
@Table(name = "auto_recommend_history",
        indexes = @Index(name = "receiverIndex", columnList = "receiver"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AutoRecommendHistory extends BaseEntity {

    /**
     * 추천 프로필 수신자
     */
    @ManyToOne
    @JoinColumn(name = "receiver")
    private UserSimpleEntity receiver;

    /**
     * 추천 프로필
     */
    @ManyToOne
    @JoinColumn(name = "recommend_profile")
    private UserSimpleEntity profile;

    /**
     * TODO
     * @param receiver
     * @param profile
     * 플러팅으로 이어진 내역 참조관계 걸기
     * 이번주 소개 프로필에 한번 소개된 사람이면 매
     * 상태 값 관리 필요할 수도 ! 자동 매칭할 때 참고해야함.삭제된 프로필인지 등등
     */
    @Enumerated(value = EnumType.STRING)
    private AutoRecommendProcessEnum autoRecommendProcessEnum;

    @Builder
    public AutoRecommendHistory(UserSimpleEntity receiver, UserSimpleEntity profile) {
        this.receiver = receiver;
        this.profile = profile;
        this.autoRecommendProcessEnum = AutoRecommendProcessEnum.INPROGRESS;
    }

    public void updateProcess(AutoRecommendProcessEnum processEnum) {
        this.autoRecommendProcessEnum = processEnum;
    }
}
