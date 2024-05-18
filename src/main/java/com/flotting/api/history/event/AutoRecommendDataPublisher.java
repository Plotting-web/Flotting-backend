package com.flotting.api.history.event;

import com.flotting.api.history.service.AutoRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AutoRecommendDataPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void createData(Long targetUserId) {
        AutoRecommendDataEvent autoRecommendData = new AutoRecommendDataEvent(this, targetUserId);
        applicationEventPublisher.publishEvent(autoRecommendData);
    }
}
