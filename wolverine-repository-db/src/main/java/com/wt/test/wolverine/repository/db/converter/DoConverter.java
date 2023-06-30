package com.wt.test.wolverine.repository.db.converter;

import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.repository.db.domain.RelationshipDbInfo;
import com.wt.test.wolverine.repository.db.model.BusinessDbDO;
import com.wt.test.wolverine.repository.db.model.RelationshipDbDO;
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
     * @param businessDbInfo BusinessDbInfo
     * @return com.wt.test.wolverine.repository.db.model.BusinessDbDO
     */
    BusinessDbDO toBusinessDbDO(BusinessDbInfo businessDbInfo);
    
    /**
     * RelationshipDbInfo 转 RelationshipDbDO
     *
     * @param relationshipDbInfo RelationshipDbInfo
     * @return com.wt.test.wolverine.repository.db.model.RelationshipDbDO
     */
    RelationshipDbDO toRelationshipDbDO(RelationshipDbInfo relationshipDbInfo);
    
}
