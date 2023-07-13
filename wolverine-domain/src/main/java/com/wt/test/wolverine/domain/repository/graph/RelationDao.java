package com.wt.test.wolverine.domain.repository.graph;

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
     * @param type        关系类型
     * @param vertexAType 节点A类型
     * @param vertexAId   节点A ID
     * @param vertexBType 节点B类型
     * @param vertexBId   节点B ID
     * @return List<RelationInfo> 关系列表
     */
    List<RelationInfo> queryEdgeBidirection(String type,
                                            String vertexAType,
                                            String vertexAId,
                                            String vertexBType,
                                            String vertexBId) {
//        edgeMapper.queryEdgeBidirection(type,vertexAType)
        return null;
    }
}
