package com.wt.test.wolverine.domain.service;


import com.wt.test.wolverine.domain.entity.BusinessInfo;

/**
 * 业务类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
public interface BusinessService {
    
    /**
     * 创建 业务类型
     *
     * @param businessInfo businessInfo
     * @return 业务类型 id
     */
    long createBusiness(BusinessInfo businessInfo);
    
    
    /**
     * 删除 业务类型
     *
     * @param businessCode 业务类型code
     * @return boolean 是否成功
     */
    boolean deleteBusiness(String businessCode);
    
    
    /**
     * 获取 业务类型
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    BusinessInfo getBusiness(String businessType);
}
