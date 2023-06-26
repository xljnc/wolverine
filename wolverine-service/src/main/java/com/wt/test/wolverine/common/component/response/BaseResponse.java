package com.wt.test.wolverine.common.component.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/6/26
 */
@Data
public class BaseResponse<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 8161557889331213724L;
    
    private Integer statusCode;
    
    private String statusMsg;
    
    private T data;
}
