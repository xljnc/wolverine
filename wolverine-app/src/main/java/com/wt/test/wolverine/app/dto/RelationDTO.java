package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/7/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -6645097609240191925L;
    
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
     * 起点业务id
     */
    private String fromId;
    
    /**
     * 起点id
     */
    private String fromVertexId;
    
    /**
     * 终点类型
     */
    private String toType;
    
    /**
     * 终点业务id
     */
    private String toId;
    
    /**
     * 终点id
     */
    private String toVertexId;
    
    /**
     * 创建时间
     */
    private Long createTime;
}
