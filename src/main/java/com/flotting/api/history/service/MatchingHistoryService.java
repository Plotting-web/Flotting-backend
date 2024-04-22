package com.flotting.api.history.service;

import com.flotting.api.history.entity.MatchingHistory;
import com.flotting.api.history.model.MatchingHistoryData;
import com.flotting.api.history.repository.MatchingHistoryRepository;
import com.flotting.api.util.type.MatchingProcessEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchingHistoryService {

    private  final MatchingHistoryRepository matchingHistoryRepository;

    @Transactional(readOnly = true)
    public List<MatchingHistoryData> getAccessibleRequestedProfile(Long requesterId) {
        List<MatchingHistory> result = matchingHistoryRepository.findByRequester_UserNoAndMatchingProcessIn(requesterId, Arrays.asList(MatchingProcessEnum.INPROGRESS, MatchingProcessEnum.SUCCESS, MatchingProcessEnum.FAIL));
        return result.stream().map(MatchingHistoryData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MatchingHistoryData> getAccessibleReceivedProfile(Long receiverId) {
        List<MatchingHistory> result = matchingHistoryRepository.findByReceiver_UserNoAndMatchingProcessIn(receiverId, Arrays.asList(MatchingProcessEnum.INPROGRESS, MatchingProcessEnum.SUCCESS));
        return result.stream().map(MatchingHistoryData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MatchingHistory> getAll() {
        return matchingHistoryRepository.findAll();
    }

    @Transactional
    public void withdraw() {
        List<MatchingHistory> matchingHistories = getAll();
        matchingHistories.forEach(matchingHistory -> {
            /**
             * 7일 지난 플러팅 매칭권 회수
             */
            LocalDateTime requestedAt = matchingHistory.getRequestedAt();
            boolean isExpired = requestedAt.plusDays(7).isBefore(LocalDateTime.now());
            //플러팅에 무응답 및 거절일 경우 둘 다, 7일동안 프로필 남아있음.
            List<MatchingProcessEnum> matchingProcessEnums = Arrays.asList(MatchingProcessEnum.FAIL, MatchingProcessEnum.INPROGRESS);
            if(matchingProcessEnums.contains(matchingHistory.getMatchingProcess()) && isExpired) {
                log.info("Matching History Requester: {} Receiver: {} requestedAt: {} isExpired: {}",
                        matchingHistory.getRequester(), matchingHistory.getReceiver(), requestedAt, isExpired);
                matchingHistory.withdraw();
            }
        });
    }

    @Transactional
    public void saveMatchingHistory(MatchingHistory matchingHistory) {
        matchingHistoryRepository.save(matchingHistory);
    }

    @Transactional(readOnly = true)
    public Set<Long> getRecommendedUsers(Long targetUserId) {
        Set<Long> result = new HashSet<>();
        result.add(targetUserId);
        List<MatchingHistory> datas = matchingHistoryRepository.findByRequester_UserNoOrReceiver_UserNo(targetUserId, targetUserId);
        datas.forEach(data -> {
            result.add(data.getRequester().getUserNo());
            result.add(data.getReceiver().getUserNo());
        });
        result.remove(targetUserId);
        log.info("getRecommendedUsers! targetUser : {} RecommendedUsers : {}", targetUserId, result.toString());
        return result;
    }
}
