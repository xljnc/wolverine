package com.wt.test.wolverine.repository.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 关系类型 do
 *
 * @author qiyu
 * @since 2023/6/27
 */
@Data
@TableName("relationship")
public class RelationshipDbDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String code;
    
    private String description;
    
    private String fromType;
    
    private String toType;
    
}
