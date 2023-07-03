package com.wt.test.wolverine.domain.service.impl;

import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.repository.cache.RelationshipCacheDao;
import com.wt.test.wolverine.domain.repository.db.RelationshipDao;
import com.wt.test.wolverine.domain.service.RelationshipService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 关系类型 service
 *
 * @author qiyu
 * @since 2023/6/29
 */
@Service
@RequiredArgsConstructor
public class RelationshipServiceImpl implements RelationshipService {
    
    private static final String RELATIONSHIP_LOCK_NAME = "lock::relationship::%s";
    
    private final RelationshipDao relationshipDao;
    
    private final RelationshipCacheDao relationshipCacheDao;
    
    private final LockUtil lockUtil;
    
    /**
     * 创建 关系类型
     *
     * @param relationshipInfo RelationshipInfo
     * @return 关系类型 id
     */
    @Override
    public Long createRelationship(RelationshipInfo relationshipInfo) {
        Long relationId = relationshipDao.createRelationship(relationshipInfo);
        //TODO 图数据库里创建edge-type
        return relationId;
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    @Override
    public boolean deleteRelationship(String relationshipCode) {
        boolean success = relationshipDao.deleteRelationship(relationshipCode);
        if (success) {
            relationshipCacheDao.deleteRelationship(relationshipCode);
            //TODO 图数据库里删除edge-type
        }
        return success;
    }
    
    /**
     * 获取 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    @Override
    public RelationshipInfo getRelationship(String relationshipCode) {
        RelationshipInfo relationshipInfo = relationshipCacheDao.getRelationship(relationshipCode);
        if (Objects.isNull(relationshipInfo)) {
            String lockName = String.format(RELATIONSHIP_LOCK_NAME, relationshipCode);
            relationshipInfo = lockUtil.lockAndExecute(lockName, this::getRelationshipWithLock, relationshipCode);
        }
        return relationshipInfo;
    }
    
    /**
     * 获取 关系类型,需要加锁
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    private RelationshipInfo getRelationshipWithLock(String relationshipCode) {
        RelationshipInfo relationshipInfo = relationshipCacheDao.getRelationship(relationshipCode);
        if (Objects.isNull(relationshipInfo)) {
            relationshipInfo = relationshipDao.getRelationship(relationshipCode);
            if (Objects.isNull(relationshipInfo)) {
                //缓存假的业务类型，防穿透
                relationshipInfo = createFakeRelationship(relationshipCode);
            }
            relationshipCacheDao.cacheRelationship(relationshipInfo);
        }
        return relationshipInfo;
    }
    
    /**
     * 创建假的 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    private static RelationshipInfo createFakeRelationship(String relationshipCode) {
        return RelationshipInfo.builder().code(relationshipCode).build();
    }
    
    /**
     * 判断是否假的 关系类型
     *
     * @param relationshipInfo 关系类型
     * @return 是否假的 关系类型
     */
    private static boolean isFakeRelationship(RelationshipInfo relationshipInfo) {
        return Optional.ofNullable(relationshipInfo).map(RelationshipInfo::getId).isEmpty();
    }
}
