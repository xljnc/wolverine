package com.wt.test.wolverine.domain.repository.cache;

import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.infra.cache.util.RedisUtil;
import com.wt.test.wolverine.infra.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * BusinessCacheDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class BusinessCacheDao {
    
    private static final String BUSINESS_REDIS_KEY = "business::%s";
    
    private final RedisUtil redisUtil;
    
    private final JsonUtil jsonUtil;
    
    /**
     * 缓存 业务类型
     *
     * @param businessInfo 业务类型
     */
    public void cacheBusiness(BusinessInfo businessInfo) {
        String businessRedisKey = createBusinessRedisKey(businessInfo.getType());
        String businessStr = jsonUtil.writeValueAsString(businessInfo);
        redisUtil.setString(businessRedisKey, businessStr);
    }
    
    /**
     * 缓存 业务类型
     *
     * @param businessInfo 业务类型
     * @param duration     缓存时间
     * @param timeUnit     TimeUnit
     */
    public void cacheBusiness(BusinessInfo businessInfo, Long duration, TimeUnit timeUnit) {
        String businessRedisKey = createBusinessRedisKey(businessInfo.getType());
        String businessStr = jsonUtil.writeValueAsString(businessInfo);
        redisUtil.setString(businessRedisKey, businessStr, duration, timeUnit);
    }
    
    /**
     * 获取 业务类型
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    public BusinessInfo getBusiness(String businessType) {
        String businessRedisKey = createBusinessRedisKey(businessType);
        String businessStr = redisUtil.getString(businessRedisKey);
        if (StringUtils.isBlank(businessStr)) {
            return null;
        }
        return jsonUtil.readValue(businessStr, BusinessInfo.class);
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessType 业务类型type
     * @return boolean 是否成功
     */
    public boolean deleteBusinessCache(String businessType) {
        String businessRedisKey = createBusinessRedisKey(businessType);
        return redisUtil.deleteKey(businessRedisKey);
    }
    
    /**
     * 组装 业务类型 redis key
     *
     * @param businessType 业务类型type
     * @return 业务类型 redis key
     */
    private static String createBusinessRedisKey(String businessType) {
        return String.format(BUSINESS_REDIS_KEY, businessType);
    }
}
