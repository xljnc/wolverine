package com.wt.test.wolverine.service;

import com.wt.test.wolverine.repository.db.domain.RelationshipDbInfo;

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
     * @param relationshipDbInfo RelationshipDbInfo
     * @return 是否成功
     */
    boolean createRelationship(RelationshipDbInfo relationshipDbInfo);
    
    
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
    RelationshipDbInfo getRelationship(String relationshipCode);
}
