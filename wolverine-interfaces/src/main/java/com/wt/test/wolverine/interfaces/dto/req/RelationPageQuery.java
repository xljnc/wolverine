package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationPageQuery implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "关系类型code不能为空")
    private String relationshipCode;
    
    @NotNull(message = "pageId不能为空")
    @Min(1L)
    private Integer pageId;
    
    @NotNull(message = "pageSize不能为空")
    @Min(1L)
    private Integer pageSize;
    
    private String fromId;
    
    private String toId;
    
}
