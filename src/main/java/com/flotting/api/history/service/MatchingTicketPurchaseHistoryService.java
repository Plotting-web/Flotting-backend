package com.flotting.api.history.service;

import com.flotting.api.history.entity.MatchingTicketPurchaseHistory;
import com.flotting.api.history.repository.MatchingTicketPurchaseHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchingTicketPurchaseHistoryService {

    private final MatchingTicketPurchaseHistoryRepository matchingTicketPurchaseHistoryRepository;

    @Transactional
    public void saveTicket(MatchingTicketPurchaseHistory matchingTicketPurchaseHistory) {
       matchingTicketPurchaseHistoryRepository.save(matchingTicketPurchaseHistory);
    }
}