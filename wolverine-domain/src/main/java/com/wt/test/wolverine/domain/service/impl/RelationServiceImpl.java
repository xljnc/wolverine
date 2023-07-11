package com.wt.test.wolverine.domain.service.impl;

import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 业务 service
 *
 * @author qiyu
 * @since 2023/7/7
 */
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    
    private final LockUtil lockUtil;
    
    /**
     * 创建 关系
     *
     * @param relationInfo RelationInfo
     */
    @Override
    public void createRelation(RelationInfo relationInfo) {
    
    }
}
