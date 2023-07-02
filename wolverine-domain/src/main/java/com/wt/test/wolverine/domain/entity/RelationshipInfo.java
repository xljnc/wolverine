package com.wt.test.wolverine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系类型 info
 * @author qiyu
 * @since 2023/6/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    private Long id;
    
    /**
     * code
     */
    private String code;
    
    /**
     * 简介
     */
    private String description;
    
    /**
     * 起点类型
     */
    private String fromType;
    
    /**
     * 终点类型
     */
    private String toType;
    
}
