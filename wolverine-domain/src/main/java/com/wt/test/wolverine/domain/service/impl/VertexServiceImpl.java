package com.wt.test.wolverine.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wt.test.wolverine.domain.common.CommonConstants;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.domain.repository.cache.VertexCacheDao;
import com.wt.test.wolverine.domain.repository.graph.VertexDao;
import com.wt.test.wolverine.domain.service.VertexService;
import com.wt.test.wolverine.infra.lock.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author qiyu
 * @since 2023/7/10
 */
@Service
@RequiredArgsConstructor
public class VertexServiceImpl implements VertexService {
    
    private static final String VERTEX_LOCK_NAME = "lock::vertex::%s";
    
    private final VertexDao vertexDao;
    
    private final VertexCacheDao vertexCacheDao;
    
    private final LockUtil lockUtil;
    
    /**
     * 创建 节点
     *
     * @param vertexInfo 节点
     */
    @Override
    public void createVertex(VertexInfo vertexInfo) {
        vertexInfo.setCreateTime(DateUtil.current());
        vertexDao.createVertex(vertexInfo);
        //清除缓存，防止缓存了假的节点
        vertexCacheDao.deleteVertex(vertexInfo.getId());
    }
    
    /**
     * 查询节点
     *
     * @param vertexId 节点id
     */
    @Override
    public VertexInfo getVertex(String vertexId) {
        VertexInfo vertexInfo = vertexCacheDao.getVertex(vertexId);
        if (Objects.isNull(vertexInfo)) {
            String lockName = String.format(VERTEX_LOCK_NAME, vertexId);
            vertexInfo = lockUtil.lockAndExecute(lockName, this::getVertexWithLock, vertexId);
        }
        if (isFakeVertex(vertexInfo)) {
            vertexInfo = null;
        }
        return vertexInfo;
    }
    
    /**
     * 查询节点, 需要加锁
     *
     * @param vertexId 节点id
     */
    private VertexInfo getVertexWithLock(String vertexId) {
        VertexInfo vertexInfo = vertexCacheDao.getVertex(vertexId);
        if (Objects.isNull(vertexInfo)) {
            vertexInfo = vertexDao.getVertex(vertexId);
            if (Objects.isNull(vertexInfo)) {
                //缓存假的业务类型，防穿透
                vertexInfo = createFakeVertex();
                vertexCacheDao.cacheVertex(vertexInfo, CommonConstants.FAKE_CACHE_TIME, CommonConstants.FAKE_CACHE_TIME_UNIT);
            } else {
                vertexCacheDao.cacheVertex(vertexInfo);
            }
        }
        return vertexInfo;
    }
    
    /**
     * 创建 假的节点
     *
     * @return VertexInfo 假的节点
     */
    private static VertexInfo createFakeVertex() {
        return VertexInfo.builder().build();
    }
    
    /**
     * 判断是否假的 节点
     *
     * @param vertexInfo 节点
     * @return 是否假的 节点
     */
    private static boolean isFakeVertex(VertexInfo vertexInfo) {
        return Optional.ofNullable(vertexInfo).map(VertexInfo::getId).isEmpty();
    }
}
