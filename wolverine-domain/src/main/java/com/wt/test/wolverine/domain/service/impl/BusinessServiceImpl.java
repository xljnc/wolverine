package com.wt.test.wolverine.domain.service.impl;

import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.repository.cache.BusinessCacheDao;
import com.wt.test.wolverine.domain.repository.db.BusinessDao;
import com.wt.test.wolverine.domain.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 业务类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    
    private final BusinessDao businessDao;
    
    private final BusinessCacheDao businessCacheDao;
    
    /**
     * 创建 业务类型
     *
     * @param businessInfo businessInfo
     * @return 业务类型 id
     */
    @Override
    public long createBusiness(BusinessInfo businessInfo) {
        Long businessId = businessDao.createBusiness(businessInfo);
        //TODO 图数据库里创建tag
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
            //TODO 图数据库里删除tag
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
            //TODO dcl
            businessInfo = businessDao.getBusiness(businessType);
            businessCacheDao.cacheBusiness(businessInfo);
        }
        return businessInfo;
    }
    
    
}
