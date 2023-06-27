package com.wt.test.wolverine.repository.db.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author qiyu
 * @since 2023/6/27
 */
@Data
public class BusinessDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    @TableId
    private Long id;
    
    private String fromType;
    
    private String toType;
    
    private String code;
}
