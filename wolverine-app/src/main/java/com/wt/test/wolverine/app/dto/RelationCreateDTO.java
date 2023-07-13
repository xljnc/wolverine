package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/6/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationCreateDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    /**
     * 关系类型code
     */
    private String relationshipCode;
    
    /**
     * 起点id不能为空
     */
    private String fromId;
    
    /**
     * 终点id不能为空
     */
    private String toId;
}
