package com.wt.test.wolverine.infra.db.dao;


import com.wt.test.wolverine.infra.db.component.BatchBaseMapper;
import com.wt.test.wolverine.infra.db.model.BusinessDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务类型 mapper
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Mapper
public interface BusinessMapper extends BatchBaseMapper<BusinessDO> {
}
