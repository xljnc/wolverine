package com.wt.test.wolverine.service.impl;

import com.wt.test.wolverine.repository.db.dao.BusinessDbDao;
import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.service.BusinessService;
import com.wt.test.wolverine.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    
    private static final String BUSINESS_REDIS_KEY = "business::%s";
    
    private final BusinessDbDao businessDbDao;
    
    private final RedisUtil redisUtil;
    
    /**
     * 创建 业务类型
     *
     * @param businessDbInfo BusinessDbInfo
     * @return 是否成功
     */
    @Override
    public boolean createBusiness(BusinessDbInfo businessDbInfo) {
        boolean success = businessDbDao.createBusiness(businessDbInfo);
        return success;
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessCode 业务类型code
     * @return boolean 是否成功
     */
    @Override
    public boolean deleteBusiness(String businessCode) {
        boolean success = businessDbDao.deleteBusiness(businessCode);
        if (success) {
            String businessRedisKey = String.format(BUSINESS_REDIS_KEY, businessCode);
            redisUtil.deleteKey(businessRedisKey);
        }
        return success;
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessCode 业务类型code
     * @return boolean 是否成功
     */
    @Override
    public BusinessDbInfo getBusiness(String businessCode) {
        return null;
    }
}
