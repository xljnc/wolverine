package com.wt.test.wolverine.infra.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 边 do
 *
 * @author qiyu
 * @since 2023/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdgeDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 5287176712316477232L;
    
    private Long id;
    
    /**
     * 边类型
     */
    private String type;
    
    /**
     * 起点类型
     */
    private String fromType;
    
    /**
     * 起点id
     */
    private String fromId;
    
    /**
     * 终点类型
     */
    private String toType;
    
    /**
     * 终点id
     */
    private String toId;
    
    /**
     * 创建时间
     */
    private Long createTime;
}
