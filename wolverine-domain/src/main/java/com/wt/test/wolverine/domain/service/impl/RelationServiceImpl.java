package com.wt.test.wolverine.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.repository.cache.RelationCacheDao;
import com.wt.test.wolverine.domain.repository.graph.RelationDao;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 业务 service
 *
 * @author qiyu
 * @since 2023/7/7
 */
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    
    private final RelationDao relationDao;
    
    private final RelationCacheDao relationCacheDao;
    
    private final LockUtil lockUtil;
    
    /**
     * 创建 关系
     *
     * @param relationInfo RelationInfo
     */
    @Override
    public void createRelation(RelationInfo relationInfo) {
        if (Objects.isNull(relationInfo.getCreateTime())) {
            relationInfo.setCreateTime(DateUtil.current());
        }
        relationDao.createRelation(relationInfo);
        //清除缓存，防止缓存了假的关系
        relationCacheDao.deleteRelation(relationInfo);
    }
}
