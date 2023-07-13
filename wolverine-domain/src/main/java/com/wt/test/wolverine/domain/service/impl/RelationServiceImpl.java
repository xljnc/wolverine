package com.wt.test.wolverine.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.repository.cache.RelationCacheDao;
import com.wt.test.wolverine.domain.repository.graph.RelationDao;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    
    /**
     * 查询节点间是否存在关系
     *
     * @param relationshipCode 关系类型
     * @param vertexAId        节点A
     * @param vertexBId        节点B
     * @return List<RelationInfo> 关系列表
     */
    @Override
    public List<RelationInfo> existsRelation(String relationshipCode, String vertexAId, String vertexBId) {
        return null;
    }
}
