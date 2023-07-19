package com.wt.test.wolverine.app.manager;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.*;
import com.wt.test.wolverine.app.util.BusinessUtil;
import com.wt.test.wolverine.app.util.VertexUtil;
import com.wt.test.wolverine.app.vo.RelationInOutVO;
import com.wt.test.wolverine.app.vo.RelationPageVO;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.domain.entity.*;
import com.wt.test.wolverine.domain.service.BusinessService;
import com.wt.test.wolverine.domain.service.RelationService;
import com.wt.test.wolverine.domain.service.RelationshipService;
import com.wt.test.wolverine.domain.service.VertexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    
    /**
     * 创建关系
     *
     * @param manageDTO 关系操作dto
     */
    public void createRelation(RelationManageDTO manageDTO) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = getRelationship(manageDTO.getRelationshipCode());
        //如果没有起始节点，先创建
        String fromVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), manageDTO.getFromId());
        VertexInfo fromVertex = vertexService.getVertex(fromVertexId);
        if (Objects.isNull(fromVertex)) {
            fromVertex = VertexInfo.builder().id(fromVertexId).tagName(relationshipInfo.getFromType()).build();
            vertexService.createVertex(fromVertex);
        }
        //如果没有结束节点，先创建
        String toVertexId = VertexUtil.createVertexId(relationshipInfo.getToType(), manageDTO.getToId());
        VertexInfo toVertex = vertexService.getVertex(toVertexId);
        if (Objects.isNull(toVertex)) {
            toVertex = VertexInfo.builder().id(toVertexId).tagName(relationshipInfo.getToType()).build();
            vertexService.createVertex(toVertex);
        }
        //创建关系
        RelationInfo relationInfo = RelationInfo.builder().relationshipCode(manageDTO.getRelationshipCode()).fromVertexId(fromVertexId).toVertexId(toVertexId).build();
        relationService.createRelation(relationInfo);
    }
    
    /**
     * 取消关系
     *
     * @param manageDTO 关系操作dto
     */
    public void cancelRelation(RelationManageDTO manageDTO) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = getRelationship(manageDTO.getRelationshipCode());
        String fromVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), manageDTO.getFromId());
        String toVertexId = VertexUtil.createVertexId(relationshipInfo.getToType(), manageDTO.getToId());
        //创建关系
        RelationInfo relationInfo = RelationInfo.builder().relationshipCode(manageDTO.getRelationshipCode()).fromVertexId(fromVertexId).toVertexId(toVertexId).build();
        relationService.deleteRelation(relationInfo);
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
     * @param biDirectionDTO 双向关系查询对象
     * @return RelationVO 关系VO
     */
    public RelationVO relationBiDirection(RelationBiDirectionDTO biDirectionDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo bizABusiness = businessService.getBusiness(biDirectionDTO.getBizTypeA());
        BusinessUtil.businessExist(bizABusiness, biDirectionDTO.getBizTypeA());
        BusinessInfo bizBBusiness = businessService.getBusiness(biDirectionDTO.getBizTypeB());
        BusinessUtil.businessExist(bizBBusiness, biDirectionDTO.getBizTypeB());
        String vertexAId = VertexUtil.createVertexId(biDirectionDTO.getBizTypeA(), biDirectionDTO.getBizIdA());
        String vertexBId = VertexUtil.createVertexId(biDirectionDTO.getBizTypeB(), biDirectionDTO.getBizIdB());
        List<RelationInfo> relationInfoList = relationService.relationBiDirection(vertexAId, vertexBId, biDirectionDTO.getRelationshipCodes());
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
    
    public RelationPageVO pageRelation(RelationPageQueryDTO pageQueryDTO) {
        //先查询关系类型是否存在
        RelationshipInfo relationshipInfo = getRelationship(pageQueryDTO.getRelationshipCode());
        String fromVertexId = null;
        String toVertexId = null;
        if (StringUtils.isNotBlank(pageQueryDTO.getFromId())) {
            fromVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), pageQueryDTO.getFromId());
        }
        if (StringUtils.isNotBlank(pageQueryDTO.getToId())) {
            toVertexId = VertexUtil.createVertexId(relationshipInfo.getFromType(), pageQueryDTO.getToId());
        }
        //获取关系分页总量
        Long count = relationService.getRelationCount(pageQueryDTO.getRelationshipCode(), fromVertexId, toVertexId);
        if (Objects.equals(count, 0L)) {
            return RelationPageVO.createEmptyVo(pageQueryDTO.getPageId(), pageQueryDTO.getPageSize());
        }
        //获取关系当前页
        List<RelationInfo> relationInfoList = relationService.queryRelation(pageQueryDTO.getRelationshipCode(),
                fromVertexId, toVertexId, pageQueryDTO.getPageId(), pageQueryDTO.getPageSize());
        return RelationPageVO.builder().relations(DtoConverter.INSTANCE.toRelationDtoList(relationInfoList))
                .count(count)
                .pageId(pageQueryDTO.getPageId())
                .pageSize(pageQueryDTO.getPageSize())
                .build();
        
    }
    
    /**
     * 获取节点间的双向关系
     *
     * @param inOutDTO 双向关系查询对象
     * @return RelationVO 关系VO
     */
    public RelationInOutVO relationInOut(RelationInOutDTO inOutDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo bizBusiness = businessService.getBusiness(inOutDTO.getBizType());
        //先查询关系类型是否存在
        getRelationship(inOutDTO.getRelationshipCode());
        BusinessUtil.businessExist(bizBusiness, inOutDTO.getBizType());
        String vertexId = VertexUtil.createVertexId(inOutDTO.getBizType(), inOutDTO.getBizId());
        List<RelationInfo> relationInfoList = relationService.relationInOut(vertexId, inOutDTO.getRelationshipCode());
        if (CollectionUtils.isEmpty(relationInfoList)) {
            return RelationInOutVO.builder()
                    .inCount(0L).inRelations(Collections.emptyList())
                    .outCount(0L).outRelations(Collections.emptyList())
                    .build();
        }
        List<RelationDTO> inRelations = new ArrayList<>();
        List<RelationDTO> outRelations = new ArrayList<>();
        relationInfoList.stream().map(DtoConverter.INSTANCE::toRelationDTO).forEach(
                relationDTO -> {
                    if (Objects.equals(relationDTO.getToId(), inOutDTO.getBizId())) {
                        inRelations.add(relationDTO);
                    } else {
                        outRelations.add(relationDTO);
                    }
                }
        );
        return RelationInOutVO.builder()
                .inCount((long) (inRelations.size())).inRelations(inRelations)
                .outCount((long) outRelations.size()).outRelations(outRelations)
                .build();
    }
    
    
    /**
     * 获取节点间的双向关系
     *
     * @param inOutDTO 双向关系查询对象
     * @return RelationVO 关系VO
     */
    public RelationInOutVO relationInOutCount(RelationInOutDTO inOutDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo bizBusiness = businessService.getBusiness(inOutDTO.getBizType());
        //先查询关系类型是否存在
        getRelationship(inOutDTO.getRelationshipCode());
        BusinessUtil.businessExist(bizBusiness, inOutDTO.getBizType());
        String vertexId = VertexUtil.createVertexId(inOutDTO.getBizType(), inOutDTO.getBizId());
        RelationCountInfo relationCountInfo = relationService.relationInOutCount(vertexId, inOutDTO.getRelationshipCode());
        return RelationInOutVO.builder()
                .outCount(relationCountInfo.getOutCount())
                .inCount(relationCountInfo.getInCount())
                .build();
    }
    
}