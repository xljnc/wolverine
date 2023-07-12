package com.wt.test.wolverine.infra.graph.dao;

import org.springframework.data.repository.query.Param;

/**
 *  图数据库 edge-type mapper
 *
 * @author qiyu
 * @since 2023/7/6
 */
public interface EdgeTypeMapper {
    
    /**
     * 创建edge-type
     *
     * @param name        edge-type名称
     * @param description 简介
     */
    void createEdgeType(@Param("name") String name, @Param("description") String description);
    
    /**
     * 删除edge-type
     *
     * @param name edge-type名称
     */
    void deleteEdgeType(@Param("name") String name);
}
