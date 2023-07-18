package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系多向查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationMultiDirectionQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    @NotBlank(message = "节点A-类型不能为空")
    private String bizTypeA;
    
    @NotBlank(message = "节点A-ID不能为空")
    private String bizIdA;
    
    @NotBlank(message = "节点B-类型不能为空")
    private String bizTypeB;
    
    @NotBlank(message = "节点B-ID不能为空")
    private String bizIdB;
    
}
