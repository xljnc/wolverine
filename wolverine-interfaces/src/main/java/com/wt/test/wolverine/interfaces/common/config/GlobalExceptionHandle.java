package com.wt.test.wolverine.interfaces.common.config;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author qiyu
 * @since 2023/7/2
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Void> handleBizException(BizException e) {
        log.error("BizException occurred。", e);
        return new BaseResponse<>(e.getCode(), e.getMessage(), null);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        log.error("Exception occurred。", e);
    }
}
