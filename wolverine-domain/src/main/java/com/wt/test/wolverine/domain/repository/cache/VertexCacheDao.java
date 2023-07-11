package com.wt.test.wolverine.domain.repository.cache;

import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.infra.cache.util.RedisUtil;
import com.wt.test.wolverine.infra.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * VertexCacheDao
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Repository
@RequiredArgsConstructor
public class VertexCacheDao {
    
    private static final String VERTEX_REDIS_KEY = "vertex::%s";
    
    private final RedisUtil redisUtil;
    
    private final JsonUtil jsonUtil;
    
    /**
     * 缓存 节点
     *
     * @param vertexInfo 节点
     */
    public void cacheVertex(VertexInfo vertexInfo) {
        String vertexRedisKey = createVertexRedisKey(vertexInfo.getId());
        String vertexStr = jsonUtil.writeValueAsString(vertexInfo);
        redisUtil.setString(vertexRedisKey, vertexStr);
    }
    
    /**
     * 缓存 节点
     *
     * @param vertexInfo 节点
     * @param duration   缓存时间
     * @param timeUnit   TimeUnit
     */
    public void cacheVertex(VertexInfo vertexInfo, Long duration, TimeUnit timeUnit) {
        String vertexRedisKey = createVertexRedisKey(vertexInfo.getId());
        String vertexStr = jsonUtil.writeValueAsString(vertexInfo);
        redisUtil.setString(vertexRedisKey, vertexStr, duration, timeUnit);
    }
    
    /**
     * 获取 节点
     *
     * @param vertexId 节点id
     * @return VertexInfo 节点
     */
    public VertexInfo getVertex(String vertexId) {
        String vertexRedisKey = createVertexRedisKey(vertexId);
        String vertexStr = redisUtil.getString(vertexRedisKey);
        if (StringUtils.isBlank(vertexStr)) {
            return null;
        }
        return jsonUtil.readValue(vertexStr, VertexInfo.class);
    }
    
    /**
     * 删除 节点
     *
     * @param vertexId 节点id
     * @return boolean 是否成功
     */
    public boolean deleteVertex(String vertexId) {
        String vertexRedisKey = createVertexRedisKey(vertexId);
        return redisUtil.deleteKey(vertexRedisKey);
    }
    
    /**
     * 组装 节点 redis key
     *
     * @param vertexId 节点id
     * @return 节点 redis key
     */
    private static String createVertexRedisKey(String vertexId) {
        return String.format(VERTEX_REDIS_KEY, vertexId);
    }
}
