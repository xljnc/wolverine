package com.wt.test.wolverine.infra.graph.dao;

import org.springframework.data.repository.query.Param;

/**
 * 创建 图数据库 tag
 * @author qiyu
 * @since 2023/7/6
 */
public interface TagMapper {
    
    /**
     * 创建tag
     *
     * @param tagName     tag名称
     * @param description 简介
     */
    void createTag(@Param("name") String tagName, @Param("description") String description);
    
    /**
     * 删除tag
     *
     * @param tagName tag名称
     */
    void deleteTag(@Param("name") String tagName);
}
