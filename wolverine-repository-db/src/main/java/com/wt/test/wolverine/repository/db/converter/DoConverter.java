package com.wt.test.wolverine.repository.db.converter;

import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.repository.db.model.BusinessDbDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * do 转换器
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Mapper
public interface DoConverter {
    
    DoConverter INSTANCE = Mappers.getMapper(DoConverter.class);
    
    /**
     * BusinessDbInfo 转 BusinessDbDO
     *
     * @param businessDbInfo 参数
     * @return com.wt.test.wolverine.repository.db.model.BusinessDbDO
     */
    BusinessDbDO toBusinessDbDO(BusinessDbInfo businessDbInfo);
    
}
