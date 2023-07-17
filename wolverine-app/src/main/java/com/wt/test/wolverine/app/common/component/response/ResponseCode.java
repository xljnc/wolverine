package com.wt.test.wolverine.app.common.component.response;

import lombok.Getter;

/**
 * @author qiyu
 * @since 2023/7/3
 */
@Getter
public enum ResponseCode {
    //通用错误，1000-9999
    PARAM_ERROR(1000,"参数错误"),
    
    //业务错误，10000 -
    BUSINESS_NOT_EXIST(10000, "业务类型不存在"),
    RELATIONSHIP_NOT_EXIST(20000, "关系类型不存在"),
    ;
    
    private final Integer code;
    
    private final String message;
    
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
