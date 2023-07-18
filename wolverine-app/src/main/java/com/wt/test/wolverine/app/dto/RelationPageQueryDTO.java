package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 关系分页查询 dto
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class RelationPageQueryDTO extends PageDtoBase{
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;
    
    private String relationshipCode;
    
    private String fromId;
    
    private String toId;
    
}
