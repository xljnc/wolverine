package com.wt.test.wolverine.manager;

import com.wt.test.wolverine.converter.DtoConverter;
import com.wt.test.wolverine.dto.RelationshipDTO;
import com.wt.test.wolverine.repository.db.domain.RelationshipDbInfo;
import com.wt.test.wolverine.service.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public String createRelationship(RelationshipDTO relationshipDTO) {
//      code = from_to
        String relationshipCode = relationshipDTO.getFromType() + "_" + relationshipDTO.getToType();
        relationshipDTO.setCode(relationshipCode);
        RelationshipDbInfo relationshipDbInfo = DtoConverter.INSTANCE.toRelationshipDbInfo(relationshipDTO);
        relationshipService.createRelationship(relationshipDbInfo);
        return relationshipCode;
    }
    
    /**
     * 删除 关系类型
     *
     * @param relationshipCode 关系类型code
     * @return boolean 是否成功
     */
    public boolean deleteRelationship(String relationshipCode) {
        return relationshipService.deleteRelationship(relationshipCode);
    }
}
