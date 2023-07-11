package com.wt.test.wolverine.domain.converter;

import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.infra.db.model.BusinessDO;
import com.wt.test.wolverine.infra.db.model.RelationshipDO;
import com.wt.test.wolverine.infra.graph.model.VertexDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * entity 转换器
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Mapper
public interface EntityConverter {
    
    EntityConverter INSTANCE = Mappers.getMapper(EntityConverter.class);
    
    /**
     * BusinessDbInfo 转 BusinessDbDO
     *
     * @param businessInfo BusinessDbInfo
     * @return com.wt.test.wolverine.repository.db.model.BusinessDbDO
     */
    BusinessDO toBusinessDO(BusinessInfo businessInfo);
    
    /**
     * BusinessDO 转 BusinessInfo
     *
     * @param businessDO BusinessDO
     * @return BusinessInfo
     */
    BusinessInfo toBusinessInfo(BusinessDO businessDO);
    
    /**
     * RelationshipDbInfo 转 RelationshipDO
     *
     * @param relationshipInfo RelationshipDbInfo
     * @return com.wt.test.wolverine.repository.db.model.RelationshipDO
     */
    RelationshipDO toRelationshipDO(RelationshipInfo relationshipInfo);
    
    /**
     * RelationshipDO 转 RelationshipInfo
     *
     * @param relationshipDO RelationshipDO
     * @return RelationshipInfo
     */
    RelationshipInfo toRelationshipInfo(RelationshipDO relationshipDO);
    
    /**
     * VertexDO 转 VertexInfo
     *
     * @param vertexDO VertexDO
     * @return VertexInfo
     */
    @Mapping(target = "createTime", expression = "java(vertexDO.getCreateTime() * 1000L)")
    VertexInfo toVertexInfo(VertexDO vertexDO);
    
    /**
     * VertexInfo 转 VertexDO
     *
     * @param vertexInfo VertexInfo
     * @return VertexDO
     */
    @Mapping(target = "createTime", expression = "java(vertexInfo.getCreateTime() / 1000L)")
    VertexDO toVertexDO(VertexInfo vertexInfo);
}
