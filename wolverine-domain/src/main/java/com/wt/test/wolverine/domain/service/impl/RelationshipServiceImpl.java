package com.wt.test.wolverine.domain.service.impl;

import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.repository.db.RelationshipDao;
import com.wt.test.wolverine.domain.service.RelationshipService;
import com.wt.test.wolverine.infra.cache.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 关系类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class RelationshipServiceImpl implements RelationshipService {
    
    private static final String RELATIONSHIP_REDIS_KEY = "relationship::%s";
    
    private final RelationshipDao relationshipDao;
    
    private final RedisUtil redisUtil;
    
    
    /**
     * 创建 关系类型
     *
     * @param relationshipInfo RelationshipInfo
     * @return 关系类型 id
     */
    @Override
    public Long createRelationship(RelationshipInfo relationshipInfo) {
        Long relationId = relationshipDao.createRelationship(relationshipInfo);
        //TODO 图数据库里创建edge-type
        return relationId;
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    @Override
    public boolean deleteRelationship(String relationshipCode) {
        boolean success = relationshipDao.deleteRelationship(relationshipCode);
        if (success) {
            String relationshipRedisKey = createRelationshipRedisKey(relationshipCode);
            redisUtil.deleteKey(relationshipRedisKey);
        }
        return success;
    }
    
    /**
     * 获取 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    @Override
    public RelationshipInfo getRelationship(String relationshipCode) {
        String relationshipRedisKey = createRelationshipRedisKey(relationshipCode);
        redisUtil.getString(relationshipRedisKey);
        return null;
    }
    
    /**
     * 组装 关系类型 redis key
     *
     * @param relationshipCode 关系类型code
     * @return 关系类型 redis key
     */
    private static String createRelationshipRedisKey(String relationshipCode) {
        return String.format(RELATIONSHIP_REDIS_KEY, relationshipCode);
    }
}
