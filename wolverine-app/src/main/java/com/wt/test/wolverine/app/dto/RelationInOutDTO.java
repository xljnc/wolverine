package com.wt.test.wolverine.app.dto;

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
public class RelationInOutDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4825293901785533984L;
    
    private String relationshipCode;
    
    private String bizType;
    
    private String bizId;
}
