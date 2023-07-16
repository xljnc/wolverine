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
 * 关系 VO
 *
 * @author qiyu
 * @since 2023/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1138579133722652107L;
    
    /**
     * 关系列表，有可能是双向的，也可能有多种
     */
    private List<RelationDTO> relations;
}
