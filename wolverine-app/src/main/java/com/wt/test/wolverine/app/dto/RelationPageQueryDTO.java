package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系分页查询 dto
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationPageQueryDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    private String relationshipCode;
    
    private Integer pageId;
    
    private Integer pageSize;
    
    private String fromId;
    
    private String toId;
    
}
