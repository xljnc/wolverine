package com.wt.test.wolverine.app.util;

import com.wt.test.wolverine.app.common.component.exception.BizException;
import com.wt.test.wolverine.app.common.component.response.ResponseCode;
import com.wt.test.wolverine.domain.entity.RelationshipInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author qiyu
 * @since 2023/7/15
 */
@UtilityClass
@Slf4j
public class RelationshipUtil {
    
    /**
     * 校验关系类型是否存在
     *
     * @param relationshipInfo 关系类型信息
     * @param relationshipCode 关系类型
     */
    public static void relationshipExist(RelationshipInfo relationshipInfo, String relationshipCode) {
        if (Objects.isNull(relationshipInfo)) {
            log.error("关系类型{}不存在", relationshipCode);
            throw new BizException(ResponseCode.RELATIONSHIP_NOT_EXIST.getCode(), ResponseCode.RELATIONSHIP_NOT_EXIST.getMessage());
        }
    }
}
