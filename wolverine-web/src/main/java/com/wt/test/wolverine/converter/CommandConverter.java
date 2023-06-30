package com.wt.test.wolverine.converter;

import com.wt.test.wolverine.dto.BusinessDTO;
import com.wt.test.wolverine.dto.RelationshipDTO;
import com.wt.test.wolverine.dto.req.BusinessCreateCommand;
import com.wt.test.wolverine.dto.req.RelationshipCreateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * command 转换器
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Mapper
public interface CommandConverter {
    
    CommandConverter INSTANCE = Mappers.getMapper(CommandConverter.class);
    
    /**
     * BusinessCreateCommand 转 BusinessDTO
     *
     * @param createCommand BusinessCreateCommand
     * @return BusinessDTO
     */
    BusinessDTO toBusinessDTO(BusinessCreateCommand createCommand);
    
    /**
     * RelationshipCreateCommand 转 RelationshipDTO
     *
     * @param createCommand RelationshipCreateCommand
     * @return RelationshipDTO
     */
    RelationshipDTO toRelationshipDTO(RelationshipCreateCommand createCommand);
    
}
