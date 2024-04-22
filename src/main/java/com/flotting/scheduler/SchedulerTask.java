package com.flotting.scheduler;

import com.flotting.api.history.service.AutoRecommendService;
import com.flotting.api.history.service.MatchingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerTask {

    private final MatchingHistoryService matchingHistoryService;
    private final AutoRecommendService autoRecommendService;

    /**
     * 매칭권 회수 & 프로필 삭제
     * 매일 12시
     */
    @Scheduled(cron = "0 0 12 * * *")
    public void withdrawMatchingTicket() {
        matchingHistoryService.withdraw();
        autoRecommendService.updateProcess();
    }

    /**
     * 플러팅 리마인드 스케쥴러
     * 수, 금, 일
     */
    @Scheduled(cron = "0 0 19 * * WED,FRI,SUN")
    public void remindReplyFlotting() {
        matchingHistoryService.withdraw();
    }

}
