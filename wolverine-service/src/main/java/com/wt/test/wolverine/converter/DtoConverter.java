package com.wt.test.wolverine.converter;

import com.wt.test.wolverine.dto.BusinessDTO;
import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * dto 转换器
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Mapper
public interface DtoConverter {
    
    DtoConverter INSTANCE = Mappers.getMapper(DtoConverter.class);
    
    /**
     * BusinessDTO 转 BusinessDbInfo
     *
     * @param businessDTO BusinessDTO
     * @return BusinessDbInfo
     */
    BusinessDbInfo toBusinessDbInfo(BusinessDTO businessDTO);
    
}
