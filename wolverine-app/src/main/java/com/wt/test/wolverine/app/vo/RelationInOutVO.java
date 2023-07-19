package com.wt.test.wolverine.app.vo;

import com.wt.test.wolverine.app.dto.RelationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author qiyu
 * @since 2023/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationInOutVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 2307533468069808824L;
    
    /**
     * 关系列表，入
     */
    private List<RelationDTO> inRelations;
    
    /**
     * 关系列表数量，入
     */
    private Long inCount;
    
    /**
     * 关系列表，出
     */
    private List<RelationDTO> outRelations;
    
    
    /**
     * 关系列表数量，出
     */
    private Long outCount;
}
