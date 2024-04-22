package com.flotting.api.history.repository;

import com.flotting.api.history.entity.MatchingHistory;
import com.flotting.api.util.type.MatchingProcessEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingHistoryRepository extends JpaRepository<MatchingHistory, Long> {

    List<MatchingHistory> findByRequester_UserNoOrReceiver_UserNo(Long requesterId, Long receiverId);
    List<MatchingHistory> findByRequester_UserNoAndMatchingProcessIn(Long requesterId, List<MatchingProcessEnum> matchingProcessEnum);
    List<MatchingHistory> findByReceiver_UserNoAndMatchingProcessIn(Long receiverId, List<MatchingProcessEnum> matchingProcessEnum);
}
