package com.wt.test.wolverine.domain.service;


import com.wt.test.wolverine.domain.entity.RelationshipInfo;

/**
 * 关系类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
public interface RelationshipService {
    
    /**
     * 创建 关系类型
     *
     * @param relationshipInfo RelationshipDbInfo
     * @return 关系类型 id
     */
    Long createRelationship(RelationshipInfo relationshipInfo);
    
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    boolean deleteRelationship(String relationshipCode);
    
    
    /**
     * 获取 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipDbInfo 关系类型
     */
    RelationshipInfo getRelationship(String relationshipCode);
}
