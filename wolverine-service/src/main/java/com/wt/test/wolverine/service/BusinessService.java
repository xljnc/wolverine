package com.wt.test.wolverine.service;

import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;

/**
 * @author qiyu
 * @since 2023/6/29
 */
public interface BusinessService {
    
    /**
     * 创建 业务类型
     *
     * @param businessDbInfo BusinessDbInfo
     * @return 是否成功
     */
    boolean createBusiness(BusinessDbInfo businessDbInfo);
}
