package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系查询 query
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageDtoBase implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5918236162149992717L;

    private Integer pageId;
    
    private Integer pageSize;
    
}
