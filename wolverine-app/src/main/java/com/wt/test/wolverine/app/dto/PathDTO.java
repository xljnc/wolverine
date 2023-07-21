package com.wt.test.wolverine.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/7/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PathDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5412232584900539946L;
    
    private String fromType;
    
    private String fromId;
    
    private String toType;
    
    private String toId;
    
    private Integer degree;
}
