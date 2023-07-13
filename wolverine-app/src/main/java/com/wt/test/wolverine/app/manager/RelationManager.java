package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.app.dto.RelationCreateDTO;
import com.wt.test.wolverine.app.util.VertexUtil;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.domain.service.RelationshipService;
import com.wt.test.wolverine.domain.service.VertexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 关系manager
 *
 * @author qiyu
 * @since 2023/7/7
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RelationManager {
    
    private final RelationshipService relationshipService;
    
    private final VertexService vertexService;
    
    private final RelationService relationService;
    
    @Transactional(rollbackFor = Exception.class)
    public void createRelation(RelationCreateDTO createDTO) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = relationshipService.getRelationship(createDTO.getRelationshipCode());
        if (Objects.isNull(relationshipInfo)) {
            throw new BizException(ResponseCode.RELATIONSHIP_NOT_EXIST.getCode(), ResponseCode.RELATIONSHIP_NOT_EXIST.getMessage());
        }
        //如果没有起始节点，先创建
        String fromVertexId = VertexUtil.getVertex(relationshipInfo.getFromType(), createDTO.getFromId());
        VertexInfo fromVertex = vertexService.getVertex(fromVertexId);
        if (Objects.isNull(fromVertex)) {
            fromVertex = VertexInfo.builder().id(fromVertexId).tagName(relationshipInfo.getFromType()).build();
            vertexService.createVertex(fromVertex);
        }
        //如果没有结束节点，先创建
        String toVertexId = VertexUtil.getVertex(relationshipInfo.getToType(), createDTO.getToId());
        VertexInfo toVertex = vertexService.getVertex(toVertexId);
        if (Objects.isNull(toVertex)) {
            toVertex = VertexInfo.builder().id(toVertexId).tagName(relationshipInfo.getToType()).build();
            vertexService.createVertex(toVertex);
        }
        //创建关系
        RelationInfo relationInfo = RelationInfo.builder().relationshipCode(createDTO.getRelationshipCode()).fromId(fromVertexId).toId(toVertexId).build();
        relationService.createRelation(relationInfo);
    }
    
    /**
     * 查询节点间是否存在关系
     *
     * @param existsDTO 查询对象
     * @return List<RelationInfo> 关系列表
     */
    public RelationVO existsRelation(String relationshipCode, String vertexA, String vertexB) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = relationshipService.getRelationship(relationshipCode);
        if (Objects.isNull(relationshipInfo)) {
            throw new BizException(ResponseCode.RELATIONSHIP_NOT_EXIST.getCode(), ResponseCode.RELATIONSHIP_NOT_EXIST.getMessage());
        }
        //重新组装节点id
        String vertexAId = VertexUtil.getVertex(relationshipInfo.getFromType(), vertexA);
        String vertexBId = VertexUtil.getVertex(relationshipInfo.getToType(), vertexB);
        List<RelationInfo> relationInfoList = relationService.existsRelation(relationshipCode, vertexAId, vertexBId);
        return RelationVO.builder().relations(relationInfoList).build();
    }
}