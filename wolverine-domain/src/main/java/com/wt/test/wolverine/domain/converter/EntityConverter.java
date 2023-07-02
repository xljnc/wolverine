package com.wt.test.wolverine.domain.converter;

import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.infra.db.model.BusinessDO;
import com.wt.test.wolverine.infra.db.model.RelationshipDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * do 转换器
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
    
}
