package com.wt.test.wolverine.infra.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 边数量 do
 *
 * @author qiyu
 * @since 2023/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdgeCountDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 5287176712316477232L;
    
    /**
     * 出度
     */
    private Long outCount;
    
    /**
     * 入度
     */
    private Long inCount;
}
