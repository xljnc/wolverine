package com.wt.test.wolverine.interfaces.dto.req;

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
    private static final long serialVersionUID = -5412232584900539946L;
    
    @NotBlank(message = "业务类型不能为空")
    private String type;
    
    private String description;
    
}
