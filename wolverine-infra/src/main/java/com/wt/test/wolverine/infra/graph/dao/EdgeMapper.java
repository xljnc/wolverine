package com.wt.test.wolverine.infra.graph.dao;

import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import org.springframework.data.repository.query.Param;

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
}
