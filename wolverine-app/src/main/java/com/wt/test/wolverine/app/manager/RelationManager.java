package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.RelationBidirectionDTO;
import com.wt.test.wolverine.app.dto.RelationCreateDTO;
import com.wt.test.wolverine.app.dto.RelationDTO;
import com.wt.test.wolverine.app.util.BusinessUtil;
import com.wt.test.wolverine.app.util.VertexUtil;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.domain.service.BusinessService;
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
    
    private final BusinessService businessService;
    
    @Transactional(rollbackFor = Exception.class)
    public void createRelation(RelationCreateDTO createDTO) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = relationshipService.getRelationship(createDTO.getRelationshipCode());
        if (Objects.isNull(relationshipInfo)) {
            throw new BizException(ResponseCode.RELATIONSHIP_NOT_EXIST.getCode(), ResponseCode.RELATIONSHIP_NOT_EXIST.getMessage());
        }
        //如果没有起始节点，先创建
        String fromVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), createDTO.getFromId());
        VertexInfo fromVertex = vertexService.getVertex(fromVertexId);
        if (Objects.isNull(fromVertex)) {
            fromVertex = VertexInfo.builder().id(fromVertexId).tagName(relationshipInfo.getFromType()).build();
            vertexService.createVertex(fromVertex);
        }
        //如果没有结束节点，先创建
        String toVertexId = VertexUtil.createVertexId(relationshipInfo.getToType(), createDTO.getToId());
        VertexInfo toVertex = vertexService.getVertex(toVertexId);
        if (Objects.isNull(toVertex)) {
            toVertex = VertexInfo.builder().id(toVertexId).tagName(relationshipInfo.getToType()).build();
            vertexService.createVertex(toVertex);
        }
        //创建关系
        RelationInfo relationInfo = RelationInfo.builder().relationshipCode(createDTO.getRelationshipCode()).fromVertexId(fromVertexId).toVertexId(toVertexId).build();
        relationService.createRelation(relationInfo);
    }
    
    /**
     * 查询节点间是否存在关系
     *
     * @param relationshipCode 关系类型code
     * @param fromId           起点业务id
     * @param toId             终点业务id
     * @return 节点间是否存在关系
     */
    public RelationDTO getRelation(String relationshipCode, String fromId, String toId) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = getRelationship(relationshipCode);
        //重新组装节点id
        String fromVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), fromId);
        String toVertexId = VertexUtil.createVertexId(relationshipInfo.getToType(), toId);
        RelationInfo relationInfo = relationService.getRelation(relationshipCode, fromVertexId, toVertexId);
        return DtoConverter.INSTANCE.toRelationDTO(relationInfo);
    }
    
    /**
     * 获取节点间的双向关系
     *
     * @param bidirectionDTO 双向关系查询对象
     * @return RelationVO 关系VO
     */
    public RelationVO relationBidirection(RelationBidirectionDTO bidirectionDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo bizABusiness = businessService.getBusiness(bidirectionDTO.getBizTypeA());
        BusinessUtil.businessExist(bizABusiness, bidirectionDTO.getBizTypeA());
        BusinessInfo bizBBusiness = businessService.getBusiness(bidirectionDTO.getBizTypeB());
        BusinessUtil.businessExist(bizBBusiness, bidirectionDTO.getBizTypeB());
        String vertexAId = VertexUtil.createVertexId(bidirectionDTO.getBizTypeA(), bidirectionDTO.getBizIdA());
        String vertexBId = VertexUtil.createVertexId(bidirectionDTO.getBizTypeB(), bidirectionDTO.getBizIdB());
        List<RelationInfo> relationInfoList = relationService.relationBidirection(vertexAId, vertexBId, bidirectionDTO.getRelationshipCodes());
        return RelationVO.builder().relations(DtoConverter.INSTANCE.toRelationDtoList(relationInfoList)).build();
    }
    
    /**
     * 获取关系类型
     *
     * @param relationshipCode 关系类型code
     * @return RelationshipInfo 关系类型
     */
    private RelationshipInfo getRelationship(String relationshipCode) {
        RelationshipInfo relationshipInfo = relationshipService.getRelationship(relationshipCode);
        if (Objects.isNull(relationshipInfo)) {
            throw new BizException(ResponseCode.RELATIONSHIP_NOT_EXIST.getCode(), ResponseCode.RELATIONSHIP_NOT_EXIST.getMessage());
        }
        return relationshipInfo;
    }
}