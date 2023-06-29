package com.wt.test.wolverine.repository.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wt.test.wolverine.repository.db.converter.DoConverter;
import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.repository.db.mapper.BusinessDbMapper;
import com.wt.test.wolverine.repository.db.model.BusinessDbDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * BusinessDbDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class BusinessDbDao {
    
    private final BusinessDbMapper businessDbMapper;
    
    /**
     * 创建 业务类型
     *
     * @param businessDbInfo
     * @return boolean 是否成功
     */
    public boolean createBusiness(BusinessDbInfo businessDbInfo) {
        BusinessDbDO businessDbDO = DoConverter.INSTANCE.toBusinessDbDO(businessDbInfo);
        return SqlHelper.retBool(businessDbMapper.insert(businessDbDO));
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessCode 业务类型code
     * @return boolean 是否成功
     */
    public boolean deleteBusiness(String businessCode) {
        LambdaQueryWrapper<BusinessDbDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BusinessDbDO::getCode, businessCode);
        return SqlHelper.retBool(businessDbMapper.delete(queryWrapper));
    }
}
