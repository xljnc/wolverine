package com.wt.test.wolverine.infra.graph.dao;

import com.wt.test.wolverine.infra.graph.model.VertexDO;
import org.springframework.data.repository.query.Param;

/**
 * 节点mapper
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
}
