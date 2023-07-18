package com.wt.test.wolverine.domain.repository.graph;

import cn.hutool.core.collection.CollUtil;
import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.infra.graph.dao.EdgeMapper;
import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关系 repository
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Repository
@RequiredArgsConstructor
public class RelationDao {
    
    private final EdgeMapper edgeMapper;
    
    /**
     * 创建关系
     *
     * @param relationInfo 关系
     */
    public void createRelation(RelationInfo relationInfo) {
        EdgeDO edgeDO = EntityConverter.INSTANCE.toEdgeDO(relationInfo);
        edgeMapper.createEdge(edgeDO);
    }
    
    /**
     * 获取关系
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @return 关系
     */
    public RelationInfo getRelation(String relationshipCode, String fromVertexId, String toVertexId) {
        EdgeDO edgeDO = edgeMapper.getEdge(relationshipCode, fromVertexId, toVertexId);
        return EntityConverter.INSTANCE.toRelation(edgeDO);
    }
    
    /**
     * 删除关系
     *
     * @param relationInfo 关系
     */
    public void deleteRelation(RelationInfo relationInfo) {
        EdgeDO edgeDO = EntityConverter.INSTANCE.toEdgeDO(relationInfo);
        edgeMapper.deleteEdge(edgeDO);
    }
    
    /**
     * 查询2个节点之间的关系
     * 双向查询
     *
     * @param vertexAId         节点A ID
     * @param vertexBId         节点B ID
     * @param relationshipCodes 关系类型列表
     * @return List<RelationInfo> 关系列表
     */
    public List<RelationInfo> queryEdgeBiDirection(String vertexAId,
                                                   String vertexBId,
                                                   List<String> relationshipCodes) {
        //如果关系类型
        String relationshipList = "";
        if (CollUtil.isNotEmpty(relationshipCodes)) {
            relationshipList = relationListToString(relationshipCodes);
        }
        List<EdgeDO> edgeDOList = edgeMapper.queryEdgeBiDirection(vertexAId, vertexBId, relationshipList);
        return EntityConverter.INSTANCE.toRelationInfoList(edgeDOList);
    }
    
    private static String relationListToString(List<String> relationshipCodes) {
        StringBuilder sb = new StringBuilder();
        relationshipCodes.forEach(code -> sb.append(":").append(code));
        return sb.toString();
    }
    
    /**
     * 获取关系数量
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @return 关系数量
     */
    public Long getRelationCount(String relationshipCode, String fromVertexId, String toVertexId) {
        return edgeMapper.getEdgeCount(relationshipCode, fromVertexId, toVertexId);
    }
    
    /**
     * 获取关系数量
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @param limit            数量
     * @param offset           偏移量
     * @return List<RelationInfo> 关系列表
     */
    public List<RelationInfo> queryRelation(String relationshipCode, String fromVertexId,
                                            String toVertexId, Long limit, Long offset) {
        List<EdgeDO> edgeDOList = edgeMapper.queryEdge(relationshipCode, fromVertexId, toVertexId, limit, offset);
        return EntityConverter.INSTANCE.toRelationInfoList(edgeDOList);
    }
    
    
    /**
     * 获取节点关系的入和出
     *
     * @param vertexId         节点id
     * @param relationshipCode 关系类型
     * @return List<RelationInfo> 关系列表
     */
    public List<RelationInfo> relationInOut(String vertexId, String relationshipCode) {
        List<EdgeDO> edgeDOList = edgeMapper.edgeInOut(vertexId, relationshipCode);
        return EntityConverter.INSTANCE.toRelationInfoList(edgeDOList);
    }
    
}
