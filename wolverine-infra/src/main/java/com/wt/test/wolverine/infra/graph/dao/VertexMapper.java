package com.wt.test.wolverine.infra.graph.dao;

import com.wt.test.wolverine.infra.graph.model.VertexDO;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 图数据库 节点 mapper
 *
 * @author qiyu
 * @since 2023/7/7
 */
public interface VertexMapper {
    
    /**
     * 创建节点
     *
     * @param vertexDO 节点do
     */
    void createVertex(@Param("vertex") VertexDO vertexDO);
    
    /**
     * 查询节点
     *
     * @param id 节点id
     * @return VertexDO 节点
     */
    VertexDO getVertex(@Param("vid") String id);
    
    /**
     * 多度节点，最少2度
     * 不返回自身
     * 不返回存在1度关系的节点
     *
     * @param vertexId 节点id
     * @param toType   目标节点类型
     * @param degree   度数，尽量不要超过3
     * @param edgeType 关系类型
     * @param limit    数量
     * @param offset   偏移量
     * @return 多度节点
     */
    List<VertexDO> pageVertexMultiDegree(@Param("vertexId") @NonNull String vertexId,
                                         @Param("toType") @NonNull String toType,
                                         @Param("degree") @NonNull Integer degree,
                                         @Param("edgeType") String edgeType,
                                         @Param("limit") @NonNull Long limit,
                                         @Param("offset") @NonNull Long offset);
}
