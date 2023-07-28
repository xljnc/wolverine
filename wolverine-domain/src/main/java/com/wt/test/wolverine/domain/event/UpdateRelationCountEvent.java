package com.wt.test.wolverine.domain.event;

import com.wt.test.wolverine.domain.entity.RelationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新关系数量事件
 *
 * @author qiyu
 * @since 2023/7/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRelationCountEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = -2451208928408529977L;
    
    /**
     * 关系信息
     */
    private RelationInfo relationInfo;
    
    /**
     * 方向
     */
    private Integer direction;
}
