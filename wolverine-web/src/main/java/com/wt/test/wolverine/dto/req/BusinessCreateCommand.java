package com.wt.test.wolverine.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessCreateCommand implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "起点类型")
    private String fromType;
    
    @NotBlank(message = "终点类型")
    private String toType;
    
    private String description;
    
}
