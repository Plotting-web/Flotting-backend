package com.flotting.api.history.controller;

import com.flotting.api.history.model.MatchingTicketPurchaseHistoryDto;
import com.flotting.api.history.service.MatchingTicketPurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MatchingTicketPurchaseController {

    private final MatchingTicketPurchaseHistoryService matchingTicketPurchaseHistoryService;

    @PostMapping("save/matching-ticket")
    public ResponseEntity<Object> saveTicket(@RequestBody MatchingTicketPurchaseHistoryDto matchingTicketPurchaseHistoryDto){
        matchingTicketPurchaseHistoryService.saveTicket(matchingTicketPurchaseHistoryDto);
        return ResponseEntity.ok().build();
    }
}
