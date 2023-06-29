package com.wt.test.wolverine.manager;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.wt.test.wolverine.converter.DtoConverter;
import com.wt.test.wolverine.dto.BusinessDTO;
import com.wt.test.wolverine.repository.db.domain.BusinessDbInfo;
import com.wt.test.wolverine.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
     * @param businessDbInfo
     * @return boolean 是否成功
     */
    public String createBusiness(BusinessDTO businessDTO) {
        //分配code
        String businessCode = NanoIdUtils.randomNanoId();
        businessDTO.setCode(businessCode);
        BusinessDbInfo businessDbInfo = DtoConverter.INSTANCE.toBusinessDbInfo(businessDTO);
        businessService.createBusiness(businessDbInfo);
        return businessCode;
    }
    
    /**
     * 删除 业务类型
     *
     * @param businessCode 业务类型code
     * @return boolean 是否成功
     */
    public boolean deleteBusiness(String businessCode) {
       return businessService.deleteBusiness(businessCode);
    }
}
