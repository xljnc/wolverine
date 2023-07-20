package com.wt.test.wolverine.app.util;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author qiyu
 * @since 2023/7/15
 */
@UtilityClass
@Slf4j
public class BusinessUtil {
    
    /**
     * 校验业务类型是否存在
     *
     * @param businessInfo 业务类型信息
     * @param type 业务类型
     */
    public static void businessExist(BusinessInfo businessInfo, String type) {
        if (Objects.isNull(businessInfo)) {
            log.error("业务类型{}不存在", type);
            throw new BizException(ResponseCode.BUSINESS_NOT_EXIST.getCode(), ResponseCode.BUSINESS_NOT_EXIST.getMessage());
        }
    }
}
