package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
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
@SuperBuilder
public class RelationBiDirectionDTO {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    /**
     * 节点A-类型
     */
    private String bizTypeA;
    
    /**
     * 节点A-ID
     */
    private String bizIdA;
    
    /**
     * 节点B-类型
     */
    private String bizTypeB;
    
    /**
     * 节点B-ID
     */
    private String bizIdB;
    
    /**
     * 关系类型列表
     */
    private List<String> relationshipCodes;
    
}
