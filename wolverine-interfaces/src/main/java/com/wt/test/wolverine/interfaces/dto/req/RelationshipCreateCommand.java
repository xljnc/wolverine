package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系类型 创建 command
 * @author qiyu
 * @since 2023/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipCreateCommand implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "code不能为空")
    private String code;
    
    @NotBlank(message = "起点类型不能为空")
    private String fromType;
    
    @NotBlank(message = "终点类型不能为空")
    private String toType;
    
    private String description;
    
}
