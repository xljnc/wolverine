package com.wt.test.wolverine.infra.graph.dao;

import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 图数据库 边 mapper
 *
 * @author qiyu
 * @since 2023/7/11
 */
public interface EdgeMapper {
    
    /**
     * 创建 边
     *
     * @param edgeDO 边do
     */
    void createEdge(@Param("edge") EdgeDO edgeDO);
    
    /**
     * 创建 边
     *
     * @param edgeDO 边do
     */
    void deleteEdge(@Param("edge") EdgeDO edgeDO);
    
    /**
     * 查询2个节点之间的关系
     * 双向查询
     *
     * @param vertexAId 节点A ID
     * @param vertexBId 节点B ID
     * @param edgeTypes 关系类型列表
     * @return java.util.List<com.wt.test.wolverine.infra.graph.model.EdgeDO>
     */
    List<EdgeDO> queryEdgeBidirection(@Param("vertexAId") String vertexAId,
                                      @Param("vertexBId") String vertexBId,
                                      @Param("edgeTypes") String edgeTypes);
}
