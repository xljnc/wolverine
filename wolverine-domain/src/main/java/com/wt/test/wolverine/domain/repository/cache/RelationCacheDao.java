package com.wt.test.wolverine.domain.repository.cache;

import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.infra.cache.util.RedisUtil;
import com.wt.test.wolverine.infra.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * RelationCacheDao
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Repository
@RequiredArgsConstructor
public class RelationCacheDao {
    
    private static final String RELATION_REDIS_KEY = "relation::%s::%s::%s";
    
    private static final String RELATION_COUNT_REDIS_KEY = "relation::count::%s::%s::%d";
    
    private final RedisUtil redisUtil;
    
    private final JsonUtil jsonUtil;
    
    /**
     * 缓存 关系
     *
     * @param relationInfo 关系
     */
    public void cacheRelation(RelationInfo relationInfo) {
        String relationRedisKey = createRelationRedisKey(relationInfo);
        String relationStr = jsonUtil.writeValueAsString(relationInfo);
        redisUtil.setString(relationRedisKey, relationStr);
    }
    
    /**
     * 缓存 关系
     *
     * @param relationInfo 关系
     * @param duration     缓存时间
     * @param timeUnit     TimeUnit
     */
    public void cacheRelation(RelationInfo relationInfo, Long duration, TimeUnit timeUnit) {
        String relationRedisKey = createRelationRedisKey(relationInfo);
        String relationStr = jsonUtil.writeValueAsString(relationInfo);
        redisUtil.setString(relationRedisKey, relationStr, duration, timeUnit);
    }
    
    /**
     * 获取 关系
     *
     * @param relationshipCode 关系类型code
     * @param fromId           起点id
     * @param toId             终点id
     * @return RelationInfo 关系
     */
    public RelationInfo getRelation(String relationshipCode, String fromId, String toId) {
        String relationRedisKey = createRelationRedisKey(relationshipCode, fromId, toId);
        String relationStr = redisUtil.getString(relationRedisKey);
        if (StringUtils.isBlank(relationStr)) {
            return null;
        }
        return jsonUtil.readValue(relationStr, RelationInfo.class);
    }
    
    /**
     * 删除 关系
     *
     * @param relationInfo 关系
     * @return boolean 是否成功
     */
    public void deleteRelation(RelationInfo relationInfo) {
        String relationRedisKey = createRelationRedisKey(relationInfo);
        redisUtil.deleteKey(relationRedisKey);
    }
    
    /**
     * 组装 关系 redis key
     *
     * @param relationInfo 关系
     * @return 关系 redis key
     */
    private static String createRelationRedisKey(RelationInfo relationInfo) {
        return createRelationRedisKey(relationInfo.getRelationshipCode(), relationInfo.getFromVertexId(), relationInfo.getToVertexId());
    }
    
    /**
     * 组装 关系 redis key
     *
     * @param relationshipCode 关系类型code
     * @param fromId           起点id
     * @param toId             终点id
     * @return 关系 redis key
     */
    private static String createRelationRedisKey(String relationshipCode, String fromId, String toId) {
        return String.format(RELATION_REDIS_KEY, relationshipCode, fromId, toId);
    }
    
    /**
     * 缓存 关系 数量
     *
     * @param relationshipCode 关系类型
     * @param vertexId         节点id
     * @param direction        方向
     * @param count            关系
     */
    public boolean increRelationCount(String relationshipCode, String vertexId, int direction, Long count) {
        String countRedisKey = createCountRedisKey(relationshipCode, vertexId, direction);
        Long result = redisUtil.increNoneNegative(countRedisKey, count);
        return result >= 0L;
    }
    
    /**
     * 缓存 关系 数量
     *
     * @param relationshipCode 关系类型
     * @param vertexId         节点id
     * @param count            关系
     */
    public void cacheRelationCount(String relationshipCode, String vertexId, int direction, Long count) {
        String countRedisKey = createCountRedisKey(relationshipCode, vertexId, direction);
        redisUtil.setString(countRedisKey, String.valueOf(count));
    }
    
    /**
     * 获取 关系 数量
     *
     * @param relationshipCode 关系类型
     * @param vertexId         节点id
     * @param direction        关系方向
     */
    public Long getRelationCount(String relationshipCode, String vertexId, int direction) {
        String countRedisKey = createCountRedisKey(relationshipCode, vertexId, direction);
        String countStr = redisUtil.getString(countRedisKey);
        return Optional.ofNullable(countStr).map(Long::valueOf).orElse(null);
    }
    
    
    /**
     * 组装 关系 redis key
     *
     * @param relationshipCode 关系类型
     * @param vertexId         节点id
     * @param direction        方向,参考CommonConstants
     * @return 关系 redis key
     */
    private static String createCountRedisKey(String relationshipCode, String vertexId, int direction) {
        return String.format(RELATION_COUNT_REDIS_KEY, relationshipCode, vertexId, direction);
    }
}
