package com.wt.test.wolverine.infra.db.model;

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
public class RelationshipDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7198501431104661998L;
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * code
     */
    private String code;
    
    /**
     * 简介
     */
    private String description;
    
    /**
     * 起点类型
     */
    private String fromType;
    
    /**
     * 终点类型
     */
    private String toType;
    
}
