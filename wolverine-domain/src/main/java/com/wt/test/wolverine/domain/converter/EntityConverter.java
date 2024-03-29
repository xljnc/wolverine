package com.wt.test.wolverine.domain.converter;

import com.wt.test.wolverine.domain.entity.*;
import com.wt.test.wolverine.infra.db.model.BusinessDO;
import com.wt.test.wolverine.infra.db.model.RelationshipDO;
import com.wt.test.wolverine.infra.graph.model.EdgeCountDO;
import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import com.wt.test.wolverine.infra.graph.model.VertexDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    @Mapping(target = "createTime", expression = "java(vertexDO.getCreateTime() == null?null:vertexDO.getCreateTime() * 1000L)")
    VertexInfo toVertexInfo(VertexDO vertexDO);
    
    /**
     * List<VertexDO> 转 List<VertexInfo>
     *
     * @param vertexDOList List<VertexDO>
     * @return List<VertexInfo>
     */
    List<VertexInfo> toVertexInfoList(List<VertexDO> vertexDOList);
    
    /**
     * VertexInfo 转 VertexDO
     *
     * @param vertexInfo VertexInfo
     * @return VertexDO
     */
    @Mapping(target = "createTime", expression = "java(vertexInfo.getCreateTime() == null?null:vertexInfo.getCreateTime() / 1000L)")
    VertexDO toVertexDO(VertexInfo vertexInfo);
    
    /**
     * RelationInfo 转 EdgeDO
     *
     * @param relationInfo RelationInfo
     * @return EdgeDO
     */
    @Mapping(target = "type", source = "relationshipCode")
    @Mapping(target = "createTime", expression = "java(relationInfo.getCreateTime() == null?null:relationInfo.getCreateTime() / 1000L)")
    EdgeDO toEdgeDO(RelationInfo relationInfo);
    
    /**
     * EdgeDO 转 RelationInfo
     *
     * @param edgeDO EdgeDO
     * @return java.util.List<com.wt.test.wolverine.domain.entity.RelationInfo>
     */
    @Mapping(target = "relationshipCode", source = "type")
    @Mapping(target = "createTime", expression = "java(edgeDO.getCreateTime() == null?null:edgeDO.getCreateTime() * 1000L)")
    RelationInfo toRelation(EdgeDO edgeDO);
    
    /**
     * List<EdgeDO> 转 List<RelationInfo>
     *
     * @param edgeDOList List<EdgeDO>
     * @return java.util.List<com.wt.test.wolverine.domain.entity.RelationInfo>
     */
    List<RelationInfo> toRelationInfoList(List<EdgeDO> edgeDOList);
    
    /**
     * EdgeCountDO 转 RelationCountInfo
     *
     * @param countDO EdgeCountDO
     * @return RelationCountInfo
     */
    RelationCountInfo toRelationCountInfo(EdgeCountDO countDO);
}
