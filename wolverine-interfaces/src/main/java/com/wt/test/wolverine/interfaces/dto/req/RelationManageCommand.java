package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系 维护 command
 *
 * @author qiyu
 * @since 2023/7/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationManageCommand implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "关系类型code不能为空")
    private String relationshipCode;
    
    @NotBlank(message = "起点id不能为空")
    private String fromId;
    
    @NotBlank(message = "终点id不能为空")
    private String toId;
    
}
