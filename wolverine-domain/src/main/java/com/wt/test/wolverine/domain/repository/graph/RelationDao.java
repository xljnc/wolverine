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
    public List<RelationInfo> queryEdgeBidirection(String vertexAId,
                                                   String vertexBId,
                                                   List<String> relationshipCodes) {
        //如果关系类型
        String relationshipList = "";
        if (CollUtil.isNotEmpty(relationshipCodes)) {
            relationshipList = relationListToString(relationshipCodes);
        }
        List<EdgeDO> edgeDOList = edgeMapper.queryEdgeBidirection(vertexAId, vertexBId, relationshipList);
        return EntityConverter.INSTANCE.toRelationInfoList(edgeDOList);
    }
    
    private static String relationListToString(List<String> relationshipCodes) {
        StringBuilder sb = new StringBuilder();
        relationshipCodes.forEach(code -> sb.append(":").append(code));
        return sb.toString();
    }
}
