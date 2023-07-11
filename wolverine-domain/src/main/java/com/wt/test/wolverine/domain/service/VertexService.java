package com.wt.test.wolverine.domain.service;

import com.wt.test.wolverine.domain.entity.VertexInfo;

/**
 * 节点service
 *
 * @author qiyu
 * @since 2023/7/10
 */
public interface VertexService {
    
    /**
     * 创建 节点
     *
     * @param vertexInfo 节点
     */
    void createVertex(VertexInfo vertexInfo);
    
    
    /**
     * 查询节点
     *
     * @param vertexId 节点id
     */
    VertexInfo getVertex(String vertexId);
    
}
