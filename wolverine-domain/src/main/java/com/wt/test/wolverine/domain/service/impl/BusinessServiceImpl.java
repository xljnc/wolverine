package com.wt.test.wolverine.domain.service.impl;

import com.wt.test.wolverine.domain.common.CommonConstants;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.repository.cache.BusinessCacheDao;
import com.wt.test.wolverine.domain.repository.db.BusinessDao;
import com.wt.test.wolverine.domain.repository.graph.TagDao;
import com.wt.test.wolverine.domain.service.BusinessService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 业务类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    
    private static final String BUSINESS_LOCK_NAME = "lock::business::%s";
    
    private final BusinessDao businessDao;
    
    private final BusinessCacheDao businessCacheDao;
    
    private final TagDao tagDao;
    
    private final LockUtil lockUtil;
    
    private final ThreadPoolExecutor commonExecutor;
    
    /**
     * 创建 业务类型
     *
     * @param businessInfo businessInfo
     * @return 业务类型 id
     */
    @Override
    public long createBusiness(BusinessInfo businessInfo) {
        Long businessId = businessDao.createBusiness(businessInfo);
        CompletableFuture.supplyAsync(() -> {
            tagDao.createTag(businessInfo.getType(), businessInfo.getDescription());
            return null;
        }, commonExecutor);
        //清除缓存，防止缓存了假的业务类型
        businessCacheDao.deleteBusinessCache(businessInfo.getType());
        return businessId;
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessType 业务类型type
     * @return boolean 是否成功
     */
    @Override
    public boolean deleteBusiness(String businessType) {
        boolean success = businessDao.deleteBusiness(businessType);
        if (success) {
            businessCacheDao.deleteBusinessCache(businessType);
            CompletableFuture.supplyAsync(() -> {
                tagDao.deleteTag(businessType);
                return null;
            }, commonExecutor);
        }
        return success;
    }
    
    /**
     * 获取 业务类型
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    @Override
    public BusinessInfo getBusiness(String businessType) {
        BusinessInfo businessInfo = businessCacheDao.getBusiness(businessType);
        if (Objects.isNull(businessInfo)) {
            String lockName = String.format(BUSINESS_LOCK_NAME, businessType);
            businessInfo = lockUtil.lockAndExecute(lockName, this::getBusinessWithLock, businessType);
        }
        if (isFakeBusiness(businessInfo)) {
            businessInfo = null;
        }
        return businessInfo;
    }
    
    /**
     * 获取 业务类型,需要加锁
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    private BusinessInfo getBusinessWithLock(String businessType) {
        BusinessInfo businessInfo = businessCacheDao.getBusiness(businessType);
        if (Objects.isNull(businessInfo)) {
            businessInfo = businessDao.getBusiness(businessType);
            if (Objects.isNull(businessInfo)) {
                //缓存假的业务类型，防穿透
                businessInfo = createFakeBusiness(businessType);
                businessCacheDao.cacheBusiness(businessInfo, CommonConstants.FAKE_CACHE_TIME, CommonConstants.FAKE_CACHE_TIME_UNIT);
            } else {
                businessCacheDao.cacheBusiness(businessInfo);
            }
        }
        return businessInfo;
    }
    
    /**
     * 创建假的 业务类型
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    private static BusinessInfo createFakeBusiness(String businessType) {
        return BusinessInfo.builder().type(businessType).build();
    }
    
    /**
     * 判断是否假的 业务类型
     *
     * @param businessInfo 业务类型
     * @return 是否假的 业务类型
     */
    private static boolean isFakeBusiness(BusinessInfo businessInfo) {
        return Optional.ofNullable(businessInfo).map(BusinessInfo::getId).isEmpty();
    }
}
