package com.wt.test.wolverine.app.manager;

import com.wt.test.wolverine.app.converter.DtoConverter;
import com.wt.test.wolverine.app.dto.RelationshipDTO;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import com.wt.test.wolverine.domain.service.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 关系类型 manager
 *
 * @author qiyu
 * @since 2023/6/26
 */
@Service
@RequiredArgsConstructor
public class RelationshipManager {
    
    private final RelationshipService relationshipService;
    
    /**
     * 创建 关系类型
     *
     * @param relationshipDTO RelationshipDTO
     * @return 关系类型 code
     */
    @Transactional(rollbackFor = Exception.class)
    public String createRelationship(RelationshipDTO relationshipDTO) {
//      code = from_to
        String relationshipCode = relationshipDTO.getFromType() + "_" + relationshipDTO.getToType();
        relationshipDTO.setCode(relationshipCode);
        RelationshipInfo relationshipInfo = DtoConverter.INSTANCE.toRelationshipDbInfo(relationshipDTO);
        relationshipService.createRelationship(relationshipInfo);
        return relationshipCode;
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
}
