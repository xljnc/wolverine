package com.wt.test.wolverine.app.common.component.exception;

import lombok.Data;

import java.io.Serial;

/**
 * @author qiyu
 * @since 2023/7/3
 */
@Data
public class BizException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 173299215349501077L;
    
    private final Integer code;
    
    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
