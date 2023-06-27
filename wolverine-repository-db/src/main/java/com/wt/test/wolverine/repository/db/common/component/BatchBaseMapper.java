package com.wt.test.wolverine.repository.db.common.component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * mybatis plus 批量基础mapper
 *
 * @author qiyu
 * @date 2021/1/23
 */
public interface BatchBaseMapper<T> extends BaseMapper<T> {
    
    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
