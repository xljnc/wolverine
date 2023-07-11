package com.wt.test.wolverine.domain.service;

import com.wt.test.wolverine.domain.entity.RelationInfo;

/**
 * 关系 service
 *
 * @author qiyu
 * @since 2023/7/7
 */
public interface RelationService {
    
    /**
     * 创建 关系
     *
     * @param relationInfo RelationInfo
     */
    void createRelation(RelationInfo relationInfo);
}
