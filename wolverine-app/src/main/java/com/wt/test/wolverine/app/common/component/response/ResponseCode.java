package com.wt.test.wolverine.app.common.component.response;

import lombok.Getter;

/**
 * @author qiyu
 * @since 2023/7/3
 */
@Getter
public enum ResponseCode {
    BUSINESS_NOT_EXIST(1000, "业务类型不存在"),
    
    ;
    
    private final Integer code;
    
    private final String message;
    
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
