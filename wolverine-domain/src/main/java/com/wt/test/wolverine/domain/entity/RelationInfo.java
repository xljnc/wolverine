package com.wt.test.wolverine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系 info
 *
 * @author qiyu
 * @since 2023/6/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    private Long id;
    
    /**
     * 关系类型code
     */
    private String relationshipCode;
    
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
