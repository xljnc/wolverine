package com.wt.test.wolverine.repository.db.mapper;


import com.wt.test.wolverine.repository.db.common.component.BatchBaseMapper;
import com.wt.test.wolverine.repository.db.model.RelationshipDbDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关系类型 mapper
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Mapper
public interface RelationshipDbMapper extends BatchBaseMapper<RelationshipDbDO> {
}
