package com.wt.test.wolverine.domain.repository.cache;

import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.infra.cache.util.RedisUtil;
import com.wt.test.wolverine.infra.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * RelationshipCacheDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class RelationshipCacheDao {
    
    private static final String RELATIONSHIP_REDIS_KEY = "relationship::%s";
    
    private final RedisUtil redisUtil;
    
    private final JsonUtil jsonUtil;
    
    /**
     * 缓存 关系类型
     *
     * @param relationshipInfo 关系类型
     */
    public void cacheRelationship(RelationshipInfo relationshipInfo) {
        String relationshipRedisKey = createRelationshipRedisKey(relationshipInfo.getCode());
        String relationshipStr = jsonUtil.writeValueAsString(relationshipInfo);
        redisUtil.setString(relationshipRedisKey, relationshipStr);
    }
    
    /**
     * 获取 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    public RelationshipInfo getRelationship(String relationshipCode) {
        String relationshipRedisKey = createRelationshipRedisKey(relationshipCode);
        String relationshipStr = redisUtil.getString(relationshipRedisKey);
        if (StringUtils.isBlank(relationshipStr)) {
            return null;
        }
        return jsonUtil.readValue(relationshipStr, RelationshipInfo.class);
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    public boolean deleteRelationship(String relationshipCode) {
        String relationshipRedisKey = createRelationshipRedisKey(relationshipCode);
        return redisUtil.deleteKey(relationshipRedisKey);
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
