package com.flotting.api.history.event;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AutoRecommendDataEvent extends ApplicationEvent {

    private Long targetUserId;

    public AutoRecommendDataEvent(Object source, Long targetUserId) {
        super(source);
        this.targetUserId = targetUserId;
    }
}
