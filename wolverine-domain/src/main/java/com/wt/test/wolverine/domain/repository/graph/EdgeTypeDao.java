package com.wt.test.wolverine.domain.repository.graph;

import com.wt.test.wolverine.infra.graph.dao.EdgeTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * edge type repository
 *
 * @author qiyu
 * @since 2023/7/6
 */
@Repository
@RequiredArgsConstructor
public class EdgeTypeDao {
    private final EdgeTypeMapper edgeTypeMapper;
    
    /**
     * 创建edge-type
     *
     * @param name        edge-type名称
     * @param description 简介
     */
    public void createEdgeType(String name, String description) {
        edgeTypeMapper.createEdgeType(name, description);
    }
    
    /**
     * 删除edge-type
     *
     * @param name edge-type名称
     */
    public void deleteEdgeType(String name) {
        edgeTypeMapper.deleteEdgeType(name);
    }
}
