package com.wt.test.wolverine.interfaces.controller.open;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.RelationBiDirectionDTO;
import com.wt.test.wolverine.app.dto.RelationDTO;
import com.wt.test.wolverine.app.dto.RelationManageDTO;
import com.wt.test.wolverine.app.dto.RelationPageQueryDTO;
import com.wt.test.wolverine.app.manager.RelationManager;
import com.wt.test.wolverine.app.vo.RelationPageVO;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.RelationBiDirectionQuery;
import com.wt.test.wolverine.interfaces.dto.req.RelationManageCommand;
import com.wt.test.wolverine.interfaces.dto.req.RelationPageQuery;
import com.wt.test.wolverine.interfaces.dto.req.RelationQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关系 接口
 *
 * @author qiyu
 * @since 2023/7/7
 */
@RestController
@RequestMapping("/open/api/relation")
@RequiredArgsConstructor
public class RelationOpenController {
    
    private final RelationManager relationManager;
    
    @PostMapping("/v1/create")
    public BaseResponse<Boolean> createRelation(@RequestBody @Valid RelationManageCommand manageCommand) {
        RelationManageDTO manageDTO = CommandConverter.INSTANCE.toRelationDTO(manageCommand);
        relationManager.createRelation(manageDTO);
        return BaseResponse.success(Boolean.TRUE);
    }
    
    @PostMapping("/v1/cancel")
    public BaseResponse<Boolean> cancelRelation(@RequestBody @Valid RelationManageCommand manageCommand) {
        RelationManageDTO manageDTO = CommandConverter.INSTANCE.toRelationDTO(manageCommand);
        relationManager.cancelRelation(manageDTO);
        return BaseResponse.success(Boolean.TRUE);
    }
    
    /**
     * 获取关系
     */
    @PostMapping("/v1/get")
    public BaseResponse<RelationDTO> getRelation(@RequestBody @Valid RelationQuery relationQuery) {
        RelationDTO relationDTO = relationManager.getRelation(relationQuery.getRelationshipCode(),
                relationQuery.getFromId(), relationQuery.getToId());
        return BaseResponse.success(relationDTO);
    }
    
    /**
     * 分页获取关系
     */
    @PostMapping("/v1/page")
    public BaseResponse<RelationPageVO> pageRelation(@RequestBody @Valid RelationPageQuery pageQuery) {
        RelationPageQueryDTO pageQueryDTO = CommandConverter.INSTANCE.toRelationPageQueryDTO(pageQuery);
        RelationPageVO pageVO = relationManager.pageRelation(pageQueryDTO);
        return BaseResponse.success(pageVO);
    }
    
    /**
     * 获取节点间的双向关系
     */
    @PostMapping("/v1/bidirection")
    public BaseResponse<RelationVO> relationBidirection(@RequestBody @Valid RelationBiDirectionQuery biDirectionQuery) {
        RelationBiDirectionDTO biDirectionDTO = CommandConverter.INSTANCE.toRelationBiDirectionDTO(biDirectionQuery);
        RelationVO relationVO = relationManager.relationBiDirection(biDirectionDTO);
        return BaseResponse.success(relationVO);
    }
}
