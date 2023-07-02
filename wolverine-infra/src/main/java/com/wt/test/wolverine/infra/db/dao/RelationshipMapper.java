package com.wt.test.wolverine.infra.db.dao;


import com.wt.test.wolverine.infra.db.component.BatchBaseMapper;
import com.wt.test.wolverine.infra.db.model.RelationshipDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关系类型 mapper
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Mapper
public interface RelationshipMapper extends BatchBaseMapper<RelationshipDO> {
}
