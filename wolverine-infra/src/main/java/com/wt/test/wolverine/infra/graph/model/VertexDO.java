package com.wt.test.wolverine.infra.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 节点 do
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VertexDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    /**
     * id
     */
    private String id;
    
    /**
     * 节点类型
     */
    private String tagName;
    
    /**
     * 创建时间
     */
    private Long createTime;
}
