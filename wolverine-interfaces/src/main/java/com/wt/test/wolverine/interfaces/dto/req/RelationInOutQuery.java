package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 节点入/出 关系查询
 *
 * @author qiyu
 * @since 2023/7/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationInOutQuery implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4825293901785533984L;
    
    @NotBlank(message = "关系类型code不能为空")
    private String relationshipCode;
    
    @NotBlank(message = "节点类型不能为空")
    private String bizType;
    
    @NotBlank(message = "节点ID不能为空")
    private String bizId;
}
