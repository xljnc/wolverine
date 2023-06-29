package com.wt.test.wolverine.common.component.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/6/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 8161557889331213724L;
    
    private static final Integer DEFAULT_STATUS_CODE = 0;
    
    private static final String DEFAULT_STATUS_MSG = "success";
    
    private Integer statusCode;
    
    private String statusMsg;
    
    private T data;
    
    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .statusCode(DEFAULT_STATUS_CODE)
                .statusMsg(DEFAULT_STATUS_MSG)
                .data(data)
                .build();
    }
}
