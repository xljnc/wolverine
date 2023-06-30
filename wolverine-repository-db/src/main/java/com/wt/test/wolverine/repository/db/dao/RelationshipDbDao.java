package com.wt.test.wolverine.repository.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wt.test.wolverine.repository.db.converter.DoConverter;
import com.wt.test.wolverine.repository.db.domain.RelationshipDbInfo;
import com.wt.test.wolverine.repository.db.mapper.RelationshipDbMapper;
import com.wt.test.wolverine.repository.db.model.RelationshipDbDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * RelationshipDbDao
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Repository
@RequiredArgsConstructor
public class RelationshipDbDao {
    
    private final RelationshipDbMapper relationshipDbMapper;
    
    /**
     * 创建 关系类型
     *
     * @param relationshipDbInfo 关系类型
     * @return boolean 是否成功
     */
    public boolean createRelationship(RelationshipDbInfo relationshipDbInfo) {
        RelationshipDbDO relationshipDbDO = DoConverter.INSTANCE.toRelationshipDbDO(relationshipDbInfo);
        return SqlHelper.retBool(relationshipDbMapper.insert(relationshipDbDO));
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationCode 关系类型code
     * @return boolean 是否成功
     */
    public boolean deleteRelationship(String relationCode) {
        LambdaQueryWrapper<RelationshipDbDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelationshipDbDO::getCode, relationCode);
        return SqlHelper.retBool(relationshipDbMapper.delete(queryWrapper));
    }
}
