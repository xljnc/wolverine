package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.RelationshipDTO;
import com.wt.test.wolverine.domain.entity.BusinessInfo;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.service.BusinessService;
import com.wt.test.wolverine.domain.service.RelationshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 关系类型 manager
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RelationshipManager {
    
    private final RelationshipService relationshipService;
    
    private final BusinessService businessService;
    
    /**
     * 创建 关系类型
     *
     * @param relationshipDTO RelationshipDTO
     * @return 关系类型 code
     */
    @Transactional(rollbackFor = Exception.class)
    public String createRelationship(RelationshipDTO relationshipDTO) {
        //需要先校验业务类型是否存在
        BusinessInfo fromBusiness = businessService.getBusiness(relationshipDTO.getFromType());
        businessExist(fromBusiness, relationshipDTO.getFromType());
        BusinessInfo toBusiness = businessService.getBusiness(relationshipDTO.getToType());
        businessExist(toBusiness, relationshipDTO.getToType());
//      code = from_to
        String relationshipCode = relationshipDTO.getFromType() + "_" + relationshipDTO.getToType();
        relationshipDTO.setCode(relationshipCode);
        RelationshipInfo relationshipInfo = DtoConverter.INSTANCE.toRelationshipDbInfo(relationshipDTO);
        relationshipService.createRelationship(relationshipInfo);
        return relationshipCode;
    }
    
    /**
     * 创建 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return 关系类型
     */
    public RelationshipDTO getRelationship(String relationshipCode) {
        RelationshipInfo relationshipInfo = relationshipService.getRelationship(relationshipCode);
        if (Objects.isNull(relationshipInfo)) {
            return null;
        }
        return DtoConverter.INSTANCE.toRelationshipDTO(relationshipInfo);
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRelationship(String relationshipCode) {
        return relationshipService.deleteRelationship(relationshipCode);
    }
    
    private static void businessExist(BusinessInfo businessInfo, String type) {
        if (Objects.isNull(businessInfo)) {
            log.error("业务类型{}不存在", type);
            throw new BizException(ResponseCode.BUSINESS_NOT_EXIST.getCode(), ResponseCode.BUSINESS_NOT_EXIST.getMessage());
        }
    }
}
