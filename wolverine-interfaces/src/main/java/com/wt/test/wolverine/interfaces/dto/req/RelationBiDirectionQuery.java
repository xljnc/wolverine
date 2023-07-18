package com.wt.test.wolverine.interfaces.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 双向关系查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationBiDirectionQuery implements Serializable {
    
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
    
    /**
     * 关系类型列表
     */
    @Size(max = 20)
    private List<String> relationshipCodes;
    
}
