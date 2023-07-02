package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.BusinessDTO;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author qiyu
 * @since 2023/6/26
 */
@Service
@RequiredArgsConstructor
public class BusinessManager {
    
    private final BusinessService businessService;
    
    /**
     * 创建 业务类型
     *
     * @param businessDTO 业务类型
     * @return id
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createBusiness(BusinessDTO businessDTO) {
        BusinessInfo businessDbInfo = DtoConverter.INSTANCE.toBusinessDbInfo(businessDTO);
        return businessService.createBusiness(businessDbInfo);
    }
    
    /**
     * 创建 业务类型
     *
     * @param businessType 业务类型type
     * @return 业务类型
     */
    public BusinessDTO getBusiness(String businessType) {
        BusinessInfo businessInfo = businessService.getBusiness(businessType);
        if (Objects.isNull(businessInfo)) {
            return null;
        }
        return DtoConverter.INSTANCE.toBusinessDTO(businessInfo);
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessType 业务类型type
     * @return boolean 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBusiness(String businessType) {
        return businessService.deleteBusiness(businessType);
    }
}
