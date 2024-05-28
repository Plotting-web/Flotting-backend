package com.flotting.api.history.event;

import com.flotting.api.history.service.AutoRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutoRecommendDataListener {

    private final AutoRecommendService autoRecommendService;

    @EventListener
    public void createAutoRecommendData(AutoRecommendDataEvent event) {
        Long targetUserId = event.getTargetUserId();
        if(autoRecommendService.alreadyRecommendedProfileThisWeek(targetUserId)) {
            log.info("이미 이번주 소개 받음. userId : {}", targetUserId);
            return;
        }

        autoRecommendService.createAutoRecommend(targetUserId);
    }
}
