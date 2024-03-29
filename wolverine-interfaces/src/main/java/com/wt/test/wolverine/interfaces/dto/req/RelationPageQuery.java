package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 关系查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationPageQuery extends BasePageQuery {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "关系类型code不能为空")
    private String relationshipCode;
    
    private String fromId;
    
    private String toId;
    
}
