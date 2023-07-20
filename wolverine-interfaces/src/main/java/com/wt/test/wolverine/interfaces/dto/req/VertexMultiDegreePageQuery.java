package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

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
public class VertexMultiDegreePageQuery extends BasePageQuery {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "起点类型不能为空")
    private String fromType;
    
    @NotBlank(message = "起点ID不能为空")
    private String fromId;
    
    @NotBlank(message = "终点类型不能为空")
    private String toType;
    
    @NotNull(message = "度数不能为空")
    @Range(min = 2L, max = 5L)
    private Integer degree;
    
    /**
     * 关系类型code
     */
    private String relationshipCode;
}
