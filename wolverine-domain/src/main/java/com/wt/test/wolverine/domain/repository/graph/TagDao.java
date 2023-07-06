package com.wt.test.wolverine.domain.repository.graph;

import com.wt.test.wolverine.infra.graph.dao.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Tag repository
 * @author qiyu
 * @since 2023/7/6
 */
@Repository
@RequiredArgsConstructor
public class TagDao {
    private final TagMapper tagMapper;
    
    /**
     * 创建tag
     *
     * @param tagName     tag名称
     * @param description 简介
     */
    public void createTag(String tagName, String description) {
        tagMapper.createTag(tagName, description);
    }
    
    /**
     * 删除tag
     *
     * @param tagName     tag名称
     */
    public void deleteTag(String tagName) {
        tagMapper.deleteTag(tagName);
    }
}
