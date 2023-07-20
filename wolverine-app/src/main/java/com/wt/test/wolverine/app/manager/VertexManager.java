package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.VertexDTO;
import com.wt.test.wolverine.app.dto.VertexMultiDegreePageDTO;
import com.wt.test.wolverine.app.util.BusinessUtil;
import com.wt.test.wolverine.app.util.RelationshipUtil;
import com.wt.test.wolverine.app.util.VertexUtil;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.entity.VertexInfo;
import com.wt.test.wolverine.domain.service.BusinessService;
import com.wt.test.wolverine.domain.service.RelationshipService;
import com.wt.test.wolverine.domain.service.VertexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author qiyu
 * @since 2023/7/20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VertexManager {
    
    private final VertexService vertexService;
    
    private final RelationshipService relationshipService;
    
    private final BusinessService businessService;
    
    public List<VertexDTO> pageMultiDegreeVertex(VertexMultiDegreePageDTO pageDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo fromBusiness = businessService.getBusiness(pageDTO.getFromType());
        BusinessUtil.businessExist(fromBusiness, pageDTO.getFromType());
        BusinessInfo toBusiness = businessService.getBusiness(pageDTO.getToType());
        BusinessUtil.businessExist(toBusiness, pageDTO.getToType());
        //先查询关系类型是否存在
        if (Objects.nonNull(pageDTO.getRelationshipCode())) {
            RelationshipInfo relationshipInfo = relationshipService.getRelationship(pageDTO.getRelationshipCode());
            RelationshipUtil.relationshipExist(relationshipInfo, pageDTO.getRelationshipCode());
        }
        String fromVertexId = VertexUtil.createVertexId(pageDTO.getFromType(), pageDTO.getFromId());
        List<VertexInfo> vertexInfoList = vertexService.pageVertexMultiDegree(fromVertexId, pageDTO.getToType(),
                pageDTO.getDegree(), pageDTO.getRelationshipCode(), pageDTO.getPageId(), pageDTO.getPageSize());
        return DtoConverter.INSTANCE.toVertexDtoList(vertexInfoList);
    }
}
