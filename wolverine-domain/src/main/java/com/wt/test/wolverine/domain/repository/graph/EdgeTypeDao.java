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
    
    private static final String EDGE_TYPE_CTIME_INDEX_NAME = "idx_%s_ctime";
    
    private final EdgeTypeMapper edgeTypeMapper;
    
    /**
     * 创建edge-type
     *
     * @param name        edge-type名称
     * @param description 简介
     */
    public void createEdgeType(String name, String description) {
        edgeTypeMapper.createEdgeType(name, description);
        //在ctime属性上创建索引
        String indexName = String.format(EDGE_TYPE_CTIME_INDEX_NAME, name);
        edgeTypeMapper.createEdgeTypeIndex(name, indexName);
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
