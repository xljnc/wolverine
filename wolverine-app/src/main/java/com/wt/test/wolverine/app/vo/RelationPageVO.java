package com.wt.test.wolverine.app.vo;

import com.wt.test.wolverine.app.dto.RelationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 关系 VO
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationPageVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1138579133722652107L;
    
    private Long count;
    
    /**
     * 关系列表
     */
    private List<RelationDTO> relations;
    
    /**
     * 当前页
     */
    private Integer pageId;
    
    /**
     * 每页数量
     */
    private Integer pageSize;
    
    public static RelationPageVO createEmptyVo(Integer pageId, Integer pageSize) {
        return RelationPageVO.builder()
                .count(0L)
                .relations(Collections.emptyList())
                .pageId(pageId)
                .pageSize(pageSize)
                .build();
    }
}
