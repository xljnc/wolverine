package com.wt.test.wolverine.domain.repository.graph;

import com.wt.test.wolverine.domain.converter.EntityConverter;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.infra.graph.dao.VertexMapper;
import com.wt.test.wolverine.infra.graph.model.VertexDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
