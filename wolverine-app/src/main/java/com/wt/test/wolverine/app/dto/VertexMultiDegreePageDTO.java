package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 节点多度分页查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class VertexMultiDegreePageDTO extends PageDtoBase {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    /**
     * 起点类型
     */
    private String fromType;
    
    /**
     * 起点ID
     */
    private String fromId;
    
    /**
     * 终点类型
     */
    private String toType;
    
    /**
     * 度数
     */
    private Integer degree;
    
    /**
     * 关系类型code
     */
    private String relationshipCode;
    
}
