package com.wt.test.wolverine.domain.repository.graph;

import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.infra.graph.dao.VertexMapper;
import com.wt.test.wolverine.infra.graph.model.VertexDO;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * 节点 repository
 *
 * @author qiyu
 * @since 2023/7/10
 */
@Repository
@RequiredArgsConstructor
public class VertexDao {
    
    private final VertexMapper vertexMapper;
    
    /**
     * 创建节点
     *
     * @param vertexInfo 节点
     */
    public void createVertex(VertexInfo vertexInfo) {
        VertexDO vertexDO = EntityConverter.INSTANCE.toVertexDO(vertexInfo);
        vertexMapper.createVertex(vertexDO);
    }
    
    /**
     * 查询节点
     *
     * @param vertexId 节点id
     */
    public VertexInfo getVertex(String vertexId) {
        VertexDO vertexDO = vertexMapper.getVertex(vertexId);
        return EntityConverter.INSTANCE.toVertexInfo(vertexDO);
    }
    
    /**
     * 多度节点，最少2度
     * 不返回自身
     * 不返回存在1度关系的节点
     *
     * @param vertexId 节点id
     * @param toType   目标节点类型
     * @param degree   度数，尽量不要超过3
     * @param edgeType 关系类型
     * @param limit    数量
     * @param offset   偏移量
     * @return 多度节点
     */
    public List<VertexInfo> pageVertexMultiDegree(@NonNull String vertexId, @NonNull String toType,
                                                  @NonNull Integer degree, String edgeType,
                                                  @NonNull Long limit, @NonNull Long offset) {
        //不查询1度节点
        if (degree < 2) {
            return Collections.emptyList();
        }
        List<VertexDO> vertexDOList = vertexMapper.pageVertexMultiDegree(vertexId, toType, degree, edgeType, limit, offset);
        return EntityConverter.INSTANCE.toVertexInfoList(vertexDOList);
    }
}
