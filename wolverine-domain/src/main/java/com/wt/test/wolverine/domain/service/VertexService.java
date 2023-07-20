package com.wt.test.wolverine.domain.service;

import com.wt.test.wolverine.domain.entity.VertexInfo;
import org.springframework.lang.NonNull;

import java.util.List;

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
    
    /**
     * 多度节点，最少2度
     * 不返回自身
     * 不返回存在1度关系的节点
     *
     * @param vertexId 节点id
     * @param toType   目标节点类型
     * @param degree   度数，尽量不要超过3
     * @param edgeType 关系类型
     * @param pageId   当前分页id
     * @param pageSize 分页数量
     * @return 多度节点
     */
    List<VertexInfo> pageVertexMultiDegree(@NonNull String vertexId, @NonNull String toType,
                                          @NonNull Integer degree, String edgeType,
                                          @NonNull Integer pageId, @NonNull Integer pageSize);
    
}
