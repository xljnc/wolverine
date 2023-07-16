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
     * 获取关系
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @return 关系
     */
    RelationInfo getRelation(String relationshipCode, String fromVertexId, String toVertexId);
    
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
