package com.wt.test.wolverine.interfaces.controller.open;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.RelationBidirectionDTO;
import com.wt.test.wolverine.app.dto.RelationCreateDTO;
import com.wt.test.wolverine.app.manager.RelationManager;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.RelationBidirectionQuery;
import com.wt.test.wolverine.interfaces.dto.req.RelationCreateCommand;
import com.wt.test.wolverine.interfaces.dto.req.RelationExistsQuery;
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
    public BaseResponse<Boolean> createRelation(@RequestBody @Valid RelationCreateCommand createCommand) {
        RelationCreateDTO createDTO = CommandConverter.INSTANCE.toRelationDTO(createCommand);
        relationManager.createRelation(createDTO);
        return BaseResponse.success(Boolean.TRUE);
    }
    
    @PostMapping("/v1/exists")
    public BaseResponse<RelationVO> existsRelation(@RequestBody @Valid RelationExistsQuery existsQuery) {
        RelationVO relationVO = relationManager.existsRelation(existsQuery.getRelationshipCode(), existsQuery.getVertexA(), existsQuery.getVertexB());
        return BaseResponse.success(relationVO);
    }
    
    /**
     * 获取节点间的双向关系
     */
    @PostMapping("/v1/bidirection")
    public BaseResponse<RelationVO> relationBidirection(@RequestBody @Valid RelationBidirectionQuery bidirectionQuery) {
        RelationBidirectionDTO bidirectionDTO = CommandConverter.INSTANCE.toRelationBidirectionDTO(bidirectionQuery);
        RelationVO relationVO = relationManager.relationBidirection(bidirectionDTO);
        return BaseResponse.success(relationVO);
    }
}
