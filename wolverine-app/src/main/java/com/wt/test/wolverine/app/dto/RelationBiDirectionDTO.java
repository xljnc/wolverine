package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RelationBiDirectionDTO extends RelationMultiDirectionDTO {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;

    /**
     * 关系类型列表
     */
    private List<String> relationshipCodes;
    
}
