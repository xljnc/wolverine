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
     * 获取关系
     *
     * @param edgeType     关系类型
     * @param fromVertexId 起点id
     * @param toVertexId   终点id
     * @return 关系
     */
    EdgeDO getEdge(@Param("edgeType") String edgeType,
                   @Param("fromVertexId") String fromVertexId,
                   @Param("toVertexId") String toVertexId);
    
    /**
     * 查询2个节点之间的关系
     * 双向查询
     *
     * @param vertexAId 节点A ID
     * @param vertexBId 节点B ID
     * @param edgeTypes 关系类型列表
     * @return java.util.List<com.wt.test.wolverine.infra.graph.model.EdgeDO>
     */
    List<EdgeDO> queryEdgeBiDirection(@Param("vertexAId") String vertexAId,
                                      @Param("vertexBId") String vertexBId,
                                      @Param("edgeTypes") String edgeTypes);
    
    /**
     * 获取关系数量
     *
     * @param edgeType     关系类型
     * @param fromVertexId 起点id
     * @param toVertexId   终点id
     * @return 关系数量
     */
    Long getEdgeCount(@Param("edgeType") String edgeType,
                      @Param("fromVertexId") String fromVertexId,
                      @Param("toVertexId") String toVertexId);
    
    /**
     * 查询关系
     *
     * @param edgeType     关系类型
     * @param fromVertexId 起点id
     * @param toVertexId   终点id
     * @param limit        数量
     * @param offset       偏移量
     * @return 关系
     */
    List<EdgeDO> queryEdge(@Param("edgeType") String edgeType,
                           @Param("fromVertexId") String fromVertexId,
                           @Param("toVertexId") String toVertexId,
                           @Param("limit") Long limit,
                           @Param("offset") Long offset);
    
    /**
     * 获取节点关系的入和出
     *
     * @param vertexId 节点id
     * @param edgeType 关系类型
     * @return List<EdgeDO> 关系列表
     */
    List<EdgeDO> edgeInOut(@Param("vertexId")String vertexId,
                           @Param("edgeType") String edgeType);
}
