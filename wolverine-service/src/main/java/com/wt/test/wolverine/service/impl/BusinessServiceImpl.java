package com.wt.test.wolverine.service.impl;

import com.wt.test.wolverine.repository.db.dao.BusinessDbDao;
import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    
    private final BusinessDbDao businessDbDao;
    
    
    /**
     * 创建 业务类型
     *
     * @param businessDbInfo BusinessDbInfo
     * @return 是否成功
     */
    @Override
    public boolean createBusiness(BusinessDbInfo businessDbInfo) {
        boolean success = businessDbDao.createBusiness(businessDbInfo);
        if (success) {
            //todo 删除缓存
        }
        return success;
    }
}
