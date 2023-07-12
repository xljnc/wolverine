package com.wt.test.wolverine.domain.repository.graph;

import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.infra.graph.dao.EdgeMapper;
import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
