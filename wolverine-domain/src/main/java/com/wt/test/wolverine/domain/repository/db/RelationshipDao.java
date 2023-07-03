package com.wt.test.wolverine.domain.repository.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.infra.db.dao.RelationshipMapper;
import com.wt.test.wolverine.infra.db.model.RelationshipDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * RelationshipDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class RelationshipDao {
    
    private final RelationshipMapper relationshipMapper;
    
    /**
     * 创建 关系类型
     *
     * @param relationshipDbInfo 关系类型
     * @return 关系类型 id
     */
    public Long createRelationship(RelationshipInfo relationshipDbInfo) {
        RelationshipDO relationshipDO = EntityConverter.INSTANCE.toRelationshipDO(relationshipDbInfo);
        relationshipMapper.insert(relationshipDO);
        return relationshipDO.getId();
    }
    
    /**
     * 获取 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    public RelationshipInfo getRelationship(String relationshipCode) {
        LambdaQueryWrapper<RelationshipDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelationshipDO::getCode, relationshipCode);
        RelationshipDO relationshipDO = relationshipMapper.selectOne(queryWrapper);
        RelationshipInfo relationshipInfo = null;
        if (Objects.nonNull(relationshipDO)) {
            relationshipInfo = EntityConverter.INSTANCE.toRelationshipInfo(relationshipDO);
        }
        return relationshipInfo;
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationCode 关系类型code
     * @return boolean 是否成功
     */
    public boolean deleteRelationship(String relationCode) {
        LambdaQueryWrapper<RelationshipDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelationshipDO::getCode, relationCode);
        return SqlHelper.retBool(relationshipMapper.delete(queryWrapper));
    }
}
