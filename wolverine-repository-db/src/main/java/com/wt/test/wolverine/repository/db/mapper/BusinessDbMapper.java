package com.wt.test.wolverine.repository.db.mapper;

import com.wt.test.wolverine.repository.db.common.component.BatchBaseMapper;
import com.wt.test.wolverine.repository.db.model.BusinessDbDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiyu
 * @since 2023/6/26
 */
@Mapper
public interface BusinessDbMapper extends BatchBaseMapper<BusinessDbDO> {
}
