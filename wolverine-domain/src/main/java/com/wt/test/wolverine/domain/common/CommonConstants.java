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
    
    /**
     * 关系方向，入
     */
    public static final int RELATION_DIRECTION_IN = 1;
    
    /**
     * 关系方向，出
     */
    public static final int RELATION_DIRECTION_OUT = 2;
    
}
