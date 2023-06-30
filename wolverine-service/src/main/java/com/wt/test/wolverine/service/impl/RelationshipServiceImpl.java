package com.wt.test.wolverine.service.impl;

import com.wt.test.wolverine.repository.db.dao.RelationshipDbDao;
import com.wt.test.wolverine.repository.db.domain.RelationshipDbInfo;
import com.wt.test.wolverine.service.RelationshipService;
import com.wt.test.wolverine.util.RedisUtil;
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
    
    private final RelationshipDbDao relationshipDbDao;
    
    private final RedisUtil redisUtil;
    
    
    /**
     * 创建 关系类型
     *
     * @param relationshipDbInfo RelationshipDbInfo
     * @return 是否成功
     */
    @Override
    public boolean createRelationship(RelationshipDbInfo relationshipDbInfo) {
        boolean success = relationshipDbDao.createRelationship(relationshipDbInfo);
        return success;
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    @Override
    public boolean deleteRelationship(String relationshipCode) {
        boolean success = relationshipDbDao.deleteRelationship(relationshipCode);
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
     * @return RelationshipDbInfo 关系类型
     */
    @Override
    public RelationshipDbInfo getRelationship(String relationshipCode) {
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
