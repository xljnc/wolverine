package com.wt.test.wolverine.domain.util;

import com.wt.test.wolverine.domain.common.CommonConstants;
import com.wt.test.wolverine.domain.event.UpdateRelationCountEvent;
import com.wt.test.wolverine.domain.service.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiyu
 * @since 2023/7/27
 */
@Configuration
@RequiredArgsConstructor
public class EventHandler {
    
    private final RelationService relationService;
    
    @Bean("eventExecutor")
    public ThreadPoolExecutor eventExecutor() {
        return new ThreadPoolExecutor(2, 4, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
    }
    
    @EventListener
    @Async("eventExecutor")
    public void updateRelationCount(UpdateRelationCountEvent event) {
        if (Objects.equals(event.getDirection(), CommonConstants.RELATION_DIRECTION_OUT)) {
            //获取关系分页总量
            Long count = relationService.getRelationCountWithCache(event.getRelationInfo().getRelationshipCode(),
                    event.getRelationInfo().getFromVertexId(), CommonConstants.RELATION_DIRECTION_OUT);
            relationService.updateRelationCountCache(event.getRelationInfo().getRelationshipCode(),
                    event.getRelationInfo().getFromVertexId(), CommonConstants.RELATION_DIRECTION_OUT, count);
        } else {
            //获取关系分页总量
            Long count = relationService.getRelationCountWithCache(event.getRelationInfo().getRelationshipCode(),
                    event.getRelationInfo().getToVertexId(), CommonConstants.RELATION_DIRECTION_IN);
            relationService.updateRelationCountCache(event.getRelationInfo().getRelationshipCode(),
                    event.getRelationInfo().getToVertexId(), CommonConstants.RELATION_DIRECTION_IN, count);
        }
    }
    
}
