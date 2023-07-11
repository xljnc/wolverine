package com.wt.test.wolverine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 节点 info
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VertexInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    private String id;
    
    /**
     * 节点类型
     */
    private String tagName;
    
    /**
     * 创建时间 , 毫秒
     */
    private Long createTime;
}
