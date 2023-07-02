package com.wt.test.wolverine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 业务类型 info
 *
 * @author qiyu
 * @since 2023/6/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    private Long id;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 简介
     */
    private String description;
    
}
