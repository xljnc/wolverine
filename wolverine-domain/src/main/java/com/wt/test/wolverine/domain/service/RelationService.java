package com.wt.test.wolverine.domain.service;

import com.wt.test.wolverine.domain.entity.RelationInfo;

import java.util.List;

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
    
    /**
     * 查询节点间是否存在关系
     *
     * @param relationshipCode 关系类型
     * @param vertexAId        节点A
     * @param vertexBId        节点B
     * @return List<RelationInfo> 关系列表
     */
    List<RelationInfo> existsRelation(String relationshipCode, String vertexAId, String vertexBId);
    
    /**
     * 获取节点间的双向关系
     *
     * @param vertexAId         节点A
     * @param vertexBId         节点B
     * @param relationshipCodes 关系类型列表
     * @return List<RelationInfo> 关系列表
     */
    List<RelationInfo> relationBidirection(String vertexAId,
                                           String vertexBId,
                                           List<String> relationshipCodes);
}
