package com.wt.test.wolverine.interfaces.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author qiyu
 * @since 2023/7/2
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    
    
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error("Exception occured。", e);
    }
}
