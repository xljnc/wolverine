package com.wt.test.wolverine.domain.common;

import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

/**
 * @author qiyu
 * @since 2023/7/10
 */
@UtilityClass
public class CommonConstants {
    
    public static final Long FAKE_CACHE_TIME = 5L;
    
    public static final TimeUnit FAKE_CACHE_TIME_UNIT = TimeUnit.MINUTES;
}
