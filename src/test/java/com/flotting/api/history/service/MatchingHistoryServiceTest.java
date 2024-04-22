package com.flotting.api.history.service;

import com.flotting.api.history.entity.MatchingHistory;
import com.flotting.api.history.entity.MatchingTicketPurchaseHistory;
import com.flotting.api.user.SampleDataMaker;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.model.UserResponseDto;
import com.flotting.api.user.service.UserService;
import com.flotting.api.util.type.MatchingProcessEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class MatchingHistoryServiceTest extends SampleDataMaker {

    @Autowired
    private MatchingHistoryService matchingHistoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchingTicketPurchaseHistoryService matchingTicketPurchaseHistoryService;

    @Test
    public void 회수테스트() {
        //given
        List<UserResponseDto> responseDtos = makeUserData();
        UserSimpleEntity receiver = userService.getSimpleUser(responseDtos.get(0).getUserNo());
        UserSimpleEntity requester = userService.getSimpleUser(responseDtos.get(1).getUserNo());
        MatchingTicketPurchaseHistory matchingTicket = MatchingTicketPurchaseHistory.builder()
                .usedAt(LocalDateTime.now())
                .userSimpleEntity(requester)
                .build();
        matchingTicketPurchaseHistoryService.saveTicket(matchingTicket);
        MatchingHistory matchingHistory = MatchingHistory.builder()
                .receiver(receiver)
                .requester(requester)
                .requestedAt(LocalDateTime.now().minusDays(4))
                .matchingTicketPurchaseHistory(matchingTicket)
                .build();
        matchingHistoryService.saveMatchingHistory(matchingHistory);

        //when
        matchingHistoryService.withdraw();

        //then
        Assertions.assertNull(matchingTicket.getUsedAt());
        Assertions.assertEquals(MatchingProcessEnum.FAIL, matchingHistory.getMatchingProcess());
    }

}