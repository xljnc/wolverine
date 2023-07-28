package com.wt.test.wolverine.domain.util;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author qiyu
 * @since 2023/7/27
 */
@Component
@RequiredArgsConstructor
public class EventUtil {
    
    private static ApplicationEventPublisher applicationEventPublisher;
    
    public static <T> void publishEvent(T event) {
        applicationEventPublisher.publishEvent(event);
    }
    
    @Resource
    public void init(ApplicationEventPublisher applicationEventPublisher) {
        EventUtil.applicationEventPublisher = applicationEventPublisher;
    }
    
    
}
