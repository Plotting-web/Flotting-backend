package com.flotting.api.history.entity;

import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.util.type.MatchingProcessEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 매칭 이력 관리 테이블
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "matching_history",
    indexes = {@Index(name = "receiverIndexs", columnList = "receiver"),
                @Index(name = "requesterIndex", columnList = "requester"),
                @Index(name = "matchingIndex", columnList = "matchingProcess")})
public class MatchingHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 요청 일자
     */
    private LocalDateTime requestedAt;

    /**
     * 승낙 일자
     */
    private LocalDateTime receivedAt;

    /**
     * 요청자
     */
    @ManyToOne
    @JoinColumn(name = "requester")
    private UserSimpleEntity requester;

    /**
     * 승낙자
     */
    @ManyToOne
    @JoinColumn(name = "receiver")
    private UserSimpleEntity receiver;

    /**
     * 매칭 상태
     */

    @Enumerated(value = EnumType.STRING)
    private MatchingProcessEnum matchingProcess;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_ticket_id")
    private MatchingTicketPurchaseHistory matchingTicketPurchaseHistory;

    public void withdraw() {
        this.matchingProcess = MatchingProcessEnum.DELETE;
        this.matchingTicketPurchaseHistory.withdraw();
    }

    @Builder
    public MatchingHistory(LocalDateTime requestedAt, UserSimpleEntity requester,
                           UserSimpleEntity receiver, MatchingTicketPurchaseHistory matchingTicketPurchaseHistory) {
        this.matchingProcess = MatchingProcessEnum.INPROGRESS;
        this.requestedAt = requestedAt;
        this.requester = requester;
        this.receiver = receiver;
        this.matchingTicketPurchaseHistory = matchingTicketPurchaseHistory;
    }
}
