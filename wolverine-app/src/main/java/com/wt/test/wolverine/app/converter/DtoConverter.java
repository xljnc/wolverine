package com.wt.test.wolverine.app.converter;

import com.wt.test.wolverine.app.dto.BusinessDTO;
import com.wt.test.wolverine.app.dto.RelationDTO;
import com.wt.test.wolverine.app.dto.RelationshipDTO;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
     * BusinessDTO 转 BusinessInfo
     *
     * @param businessDTO BusinessDTO
     * @return BusinessInfo
     */
    BusinessInfo toBusinessDbInfo(BusinessDTO businessDTO);
    
    /**
     * BusinessInfo 转 BusinessDTO
     *
     * @param businessInfo BusinessInfo
     * @return BusinessDTO
     */
    BusinessDTO toBusinessDTO(BusinessInfo businessInfo);
    
    /**
     * RelationshipDTO 转 RelationshipInfo
     *
     * @param relationshipDTO RelationshipDTO
     * @return RelationshipInfo
     */
    RelationshipInfo toRelationshipDbInfo(RelationshipDTO relationshipDTO);
    
    /**
     * RelationshipInfo 转 RelationshipDTO
     *
     * @param relationshipInfo RelationshipInfo
     * @return RelationshipDTO
     */
    RelationshipDTO toRelationshipDTO(RelationshipInfo relationshipInfo);
    
    /**
     * RelationInfo 转 RelationDTO
     *
     * @param relationInfo RelationInfo
     * @return RelationDTO
     */
    @Mapping(target = "fromId", expression = "java(com.wt.test.wolverine.app.util.VertexUtil.getBizId(relationInfo.getFromVertexId()))")
    @Mapping(target = "toId", expression = "java(com.wt.test.wolverine.app.util.VertexUtil.getBizId(relationInfo.getToVertexId()))")
    RelationDTO toRelationDTO(RelationInfo relationInfo);
    
    /**
     * List<RelationInfo> 转 List<RelationDTO>
     *
     * @param relationInfoList List<RelationInfo>
     * @return List<RelationDTO>
     */
    List<RelationDTO> toRelationDtoList(List<RelationInfo> relationInfoList);
    
}
