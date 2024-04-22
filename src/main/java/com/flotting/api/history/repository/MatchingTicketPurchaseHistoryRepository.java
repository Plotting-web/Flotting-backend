package com.flotting.api.history.repository;

import com.flotting.api.history.entity.MatchingTicketPurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingTicketPurchaseHistoryRepository extends JpaRepository<MatchingTicketPurchaseHistory, Long> {
}
