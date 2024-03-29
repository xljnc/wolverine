package com.wt.test.wolverine.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wt.test.wolverine.domain.common.CommonConstants;
import com.wt.test.wolverine.domain.entity.RelationCountInfo;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.event.UpdateRelationCountEvent;
import com.wt.test.wolverine.domain.repository.cache.RelationCacheDao;
import com.wt.test.wolverine.domain.repository.graph.RelationDao;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.domain.util.EventUtil;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 业务 service
 *
 * @author qiyu
 * @since 2023/7/7
 */
@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    
    private static final String RELATION_LOCK_NAME = "lock::relation::%s::%s::%s";
    
    private static final String RELATION_COUNT_LOCK_NAME = "lock::relation::count::%s::%s::%d";
    
    private final RelationDao relationDao;
    
    private final RelationCacheDao relationCacheDao;
    
    private final LockUtil lockUtil;
    
    /**
     * 创建 关系
     *
     * @param relationInfo RelationInfo
     */
    @Override
    public void createRelation(RelationInfo relationInfo) {
        if (Objects.isNull(relationInfo.getCreateTime())) {
            relationInfo.setCreateTime(DateUtil.current());
        }
        relationDao.createRelation(relationInfo);
        //清除缓存，防止缓存了假的关系
        relationCacheDao.deleteRelation(relationInfo);
        //缓存关系数量
        updateRelationCount(relationInfo, 1L);
    }
    
    /**
     * 删除 关系
     *
     * @param relationInfo RelationInfo
     */
    @Override
    public void deleteRelation(RelationInfo relationInfo) {
        relationDao.deleteRelation(relationInfo);
        relationCacheDao.deleteRelation(relationInfo);
        //缓存关系数量
        updateRelationCount(relationInfo, -1L);
    }
    
    /**
     * 更新关系数量缓存
     *
     * @param relationInfo 关系信息
     * @param count        变更数量
     */
    private void updateRelationCount(RelationInfo relationInfo, Long count) {
        //缓存关系数量,入向
        boolean countSuccess = relationCacheDao.increRelationCount(relationInfo.getRelationshipCode(),
                relationInfo.getToVertexId(), CommonConstants.RELATION_DIRECTION_IN, count);
        if (!countSuccess) {
            UpdateRelationCountEvent inCountEvent = UpdateRelationCountEvent.builder()
                    .relationInfo(relationInfo).direction(CommonConstants.RELATION_DIRECTION_IN).build();
            EventUtil.publishEvent(inCountEvent);
        }
        //缓存关系数量,出向
        countSuccess = relationCacheDao.increRelationCount(relationInfo.getRelationshipCode(),
                relationInfo.getFromVertexId(), CommonConstants.RELATION_DIRECTION_OUT, count);
        if (!countSuccess) {
            UpdateRelationCountEvent outCountEvent = UpdateRelationCountEvent.builder()
                    .relationInfo(relationInfo).direction(CommonConstants.RELATION_DIRECTION_OUT).build();
            EventUtil.publishEvent(outCountEvent);
        }
    }
    
    /**
     * 获取关系
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @return 关系
     */
    @Override
    public RelationInfo getRelation(String relationshipCode, String fromVertexId, String toVertexId) {
        RelationInfo relationInfo = relationCacheDao.getRelation(relationshipCode, fromVertexId, toVertexId);
        if (Objects.isNull(relationInfo)) {
            String lockName = String.format(RELATION_LOCK_NAME, relationshipCode, fromVertexId, toVertexId);
            List<String> args = Arrays.asList(relationshipCode, fromVertexId, toVertexId);
            relationInfo = lockUtil.lockAndExecute(lockName, this::getRelationWithLock, args);
        }
        if (isFakeRelation(relationInfo)) {
            relationInfo = null;
        }
        return relationInfo;
    }
    
    /**
     * 查询关系, 需要加锁
     *
     * @param args 参数, arg[0]:relationshipCode, arg[1]:fromVertexId, arg[2]:toVertexId
     */
    private RelationInfo getRelationWithLock(List<String> args) {
        String relationshipCode = args.get(0);
        String fromVertexId = args.get(1);
        String toVertexId = args.get(2);
        RelationInfo relationInfo = relationCacheDao.getRelation(relationshipCode, fromVertexId, toVertexId);
        if (Objects.isNull(relationInfo)) {
            relationInfo = relationDao.getRelation(relationshipCode, fromVertexId, toVertexId);
            if (Objects.isNull(relationInfo)) {
                //缓存假的业务类型，防穿透
                relationInfo = createFakeRelation();
                relationCacheDao.cacheRelation(relationInfo, CommonConstants.FAKE_CACHE_TIME, CommonConstants.FAKE_CACHE_TIME_UNIT);
            } else {
                relationCacheDao.cacheRelation(relationInfo);
            }
        }
        return relationInfo;
    }
    
    /**
     * 创建 假的关系
     *
     * @return RelationInfo 假的关系
     */
    private static RelationInfo createFakeRelation() {
        return RelationInfo.builder().build();
    }
    
    /**
     * 判断是否假的 关系
     *
     * @param relationInfo 关系
     * @return 是否假的 关系
     */
    private static boolean isFakeRelation(RelationInfo relationInfo) {
        return Optional.ofNullable(relationInfo).map(RelationInfo::getRelationshipCode).isEmpty();
    }
    
    /**
     * 获取节点间的双向关系
     *
     * @param vertexAId         节点A
     * @param vertexBId         节点B
     * @param relationshipCodes 关系类型列表
     * @return List<RelationInfo> 关系列表
     */
    @Override
    public List<RelationInfo> relationBiDirection(String vertexAId, String vertexBId, List<String> relationshipCodes) {
        return relationDao.queryEdgeBiDirection(vertexAId, vertexBId, relationshipCodes);
    }
    
    /**
     * 获取关系数量
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @return 关系数量
     */
    @Override
    public Long getRelationCount(String relationshipCode, String fromVertexId, String toVertexId) {
        return relationDao.getRelationCount(relationshipCode, fromVertexId, toVertexId);
    }
    
    /**
     * 从缓存获取关系数量
     *
     * @param relationshipCode 关系类型code
     * @param vertexId         节点id
     * @param direction        关系方向
     * @return 关系数量
     */
    @Override
    public Long getRelationCountWithCache(String relationshipCode, String vertexId, int direction) {
        Long count = relationCacheDao.getRelationCount(relationshipCode, vertexId, direction);
        if (Objects.isNull(count)) {
            String lockName = String.format(RELATION_COUNT_LOCK_NAME, relationshipCode, vertexId, direction);
            List<String> args = Arrays.asList(relationshipCode, vertexId, String.valueOf(direction));
            count = lockUtil.lockAndExecute(lockName, this::getRelationCountWithLock, args);
        }
        return count;
    }
    
    /**
     * 查询关系, 需要加锁
     *
     * @param args 参数, arg[0]:relationshipCode, arg[1]:fromVertexId, arg[2]:toVertexId
     */
    private Long getRelationCountWithLock(List<String> args) {
        String relationshipCode = args.get(0);
        String vertexId = args.get(1);
        Integer direction = Integer.valueOf(args.get(2));
        Long count = relationCacheDao.getRelationCount(relationshipCode, vertexId, direction);
        if (Objects.isNull(count)) {
            //查db
            String fromVertexId = null, toVertexId = null;
            if (Objects.equals(direction, CommonConstants.RELATION_DIRECTION_OUT)) {
                fromVertexId = vertexId;
            } else {
                toVertexId = vertexId;
            }
            count = getRelationCount(relationshipCode, fromVertexId, toVertexId);
            relationCacheDao.cacheRelationCount(relationshipCode, vertexId, direction, count);
        }
        return count;
    }
    
    /**
     * 更新关系数量缓存
     *
     * @param relationshipCode 关系类型code
     * @param vertexId         节点id
     * @param direction        关系方向
     * @param count            关系数量
     */
    @Override
    public void updateRelationCountCache(String relationshipCode, String vertexId, int direction, Long count) {
        relationCacheDao.cacheRelationCount(relationshipCode, vertexId, direction, count);
    }
    
    /**
     * 获取关系数量
     *
     * @param relationshipCode 关系类型code
     * @param fromVertexId     起点id
     * @param toVertexId       终点id
     * @param pageId           当前分页id
     * @param pageSize         分页数量
     * @return List<RelationInfo> 关系列表
     */
    @Override
    public List<RelationInfo> queryRelation(String relationshipCode, String fromVertexId, String toVertexId,
                                            Integer pageId, Integer pageSize) {
        Long offset = (pageId - 1) * (long) pageSize;
        return relationDao.queryRelation(relationshipCode, fromVertexId, toVertexId, Long.valueOf(pageSize), offset);
    }
    
    /**
     * 获取节点关系的入和出
     *
     * @param vertexId         节点id
     * @param relationshipCode 关系类型
     * @return List<RelationInfo> 关系列表
     */
    @Override
    public List<RelationInfo> relationInOut(String vertexId, String relationshipCode) {
        return relationDao.relationInOut(vertexId, relationshipCode);
    }
    
    /**
     * 获取节点关系的出入数量
     *
     * @param vertexId         节点id
     * @param relationshipCode 关系类型
     * @return 关系的出入数量
     */
    @Override
    public RelationCountInfo relationInOutCount(String vertexId, String relationshipCode) {
        return relationDao.relationInOutCount(vertexId, relationshipCode);
    }
    
    /**
     * 获取节点间的最短路径
     *
     * @param fromVertexId 起点id
     * @param toVertexId   终点id
     * @param degree       最大度数
     * @return List<RelationInfo> 路径
     */
    @Override
    public List<RelationInfo> shortestPathToVertex(String fromVertexId, String toVertexId, @Nullable Integer degree) {
        return relationDao.shortestPathToVertex(fromVertexId, toVertexId, degree);
    }
}
