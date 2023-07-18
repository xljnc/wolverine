package com.wt.test.wolverine.interfaces.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

/**
 * 双向关系查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationBiDirectionQuery extends RelationMultiDirectionQuery {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;

    /**
     * 关系类型列表
     */
    private List<String> relationshipCodes;
    
}
