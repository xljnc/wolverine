package com.wt.test.wolverine.interfaces.converter;

import com.wt.test.wolverine.app.dto.*;
import com.wt.test.wolverine.interfaces.dto.req.*;
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
    
    /**
     * RelationManageCommand 转 RelationManageDTO
     *
     * @param manageCommand RelationManageCommand
     * @return RelationManageDTO
     */
    RelationManageDTO toRelationDTO(RelationManageCommand manageCommand);
    
    /**
     * RelationBiDirectionQuery 转 RelationBiDirectionDTO
     *
     * @param biDirectionQuery RelationBiDirectionQuery
     * @return RelationBiDirectionDTO
     */
    RelationBiDirectionDTO toRelationBiDirectionDTO(RelationBiDirectionQuery biDirectionQuery);
    
    /**
     * RelationPageQuery 转 RelationPageQueryDTO
     *
     * @param pageQuery RelationPageQuery
     * @return RelationPageQueryDTO
     */
    RelationPageQueryDTO toRelationPageQueryDTO(RelationPageQuery pageQuery);
    
}
