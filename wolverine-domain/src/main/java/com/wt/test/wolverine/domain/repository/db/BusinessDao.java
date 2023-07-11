package com.wt.test.wolverine.domain.repository.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.infra.db.dao.BusinessMapper;
import com.wt.test.wolverine.infra.db.model.BusinessDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * BusinessDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class BusinessDao {
    
    private final BusinessMapper businessMapper;
    
    /**
     * 创建 业务类型
     *
     * @param businessInfo 业务类型
     * @return 业务类型id
     */
    public Long createBusiness(BusinessInfo businessInfo) {
        BusinessDO businessDO = EntityConverter.INSTANCE.toBusinessDO(businessInfo);
        businessMapper.insert(businessDO);
        return businessDO.getId();
    }
    
    /**
     * 获取 业务类型
     *
     * @param businessType 业务类型type
     * @return BusinessInfo 业务类型
     */
    public BusinessInfo getBusiness(String businessType) {
        LambdaQueryWrapper<BusinessDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BusinessDO::getType, businessType);
        BusinessDO businessDO = businessMapper.selectOne(queryWrapper);
        return EntityConverter.INSTANCE.toBusinessInfo(businessDO);
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessType 业务类型type
     * @return boolean 是否成功
     */
    public boolean deleteBusiness(String businessType) {
        LambdaQueryWrapper<BusinessDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BusinessDO::getType, businessType);
        return SqlHelper.retBool(businessMapper.delete(queryWrapper));
    }
}
