package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/7/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PathQuery implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5412232584900539946L;
    
    @NotBlank(message = "起点类型不能为空")
    private String fromType;
    
    @NotBlank(message = "起点ID不能为空")
    private String fromId;
    
    @NotBlank(message = "终点类型不能为空")
    private String toType;
    
    @NotBlank(message = "终点ID不能为空")
    private String toId;
    
    @Range(max = 5L)
    private Integer degree;
}
