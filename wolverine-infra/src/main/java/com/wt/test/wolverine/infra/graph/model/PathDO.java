package com.wt.test.wolverine.infra.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiyu
 * @since 2023/7/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PathDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -1527589134657151050L;
    
    private List<EdgeDO> edgeDOList = new ArrayList<>();

}
