package com.wt.test.wolverine.interfaces.controller.inner;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.PathDTO;
import com.wt.test.wolverine.app.dto.RelationInOutDTO;
import com.wt.test.wolverine.app.dto.RelationPageQueryDTO;
import com.wt.test.wolverine.app.manager.RelationManager;
import com.wt.test.wolverine.app.vo.RelationInOutVO;
import com.wt.test.wolverine.app.vo.RelationPageVO;
import com.wt.test.wolverine.app.vo.RelationVO;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.PathQuery;
import com.wt.test.wolverine.interfaces.dto.req.RelationInOutQuery;
import com.wt.test.wolverine.interfaces.dto.req.RelationPageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关系内部接口
 * @author qiyu
 * @since 2023/7/18
 */
@RestController
@RequestMapping("/inner/api/relation")
@RequiredArgsConstructor
public class RelationInnerController {
    
    private final RelationManager relationManager;
    
    /**
     * 获取节点关系的入和出
     */
    @PostMapping("/v1/inout")
    public BaseResponse<RelationInOutVO> relationInOut(@RequestBody @Valid RelationInOutQuery inOutQuery) {
        RelationInOutDTO inOutDTO = CommandConverter.INSTANCE.toRelationInOutDTO(inOutQuery);
        RelationInOutVO inOutVO = relationManager.relationInOut(inOutDTO);
        return BaseResponse.success(inOutVO);
    }
    
    /**
     * 分页获取关系
     */
    @PostMapping("/v1/page")
    public BaseResponse<RelationPageVO> pageRelation(@RequestBody @Valid RelationPageQuery pageQuery) {
        RelationPageQueryDTO pageQueryDTO = CommandConverter.INSTANCE.toRelationPageQueryDTO(pageQuery);
        RelationPageVO pageVO = relationManager.innerPageRelation(pageQueryDTO);
        return BaseResponse.success(pageVO);
    }
    
    /**
     * 获取节点间最短路径
     */
    @PostMapping("/v1/shortest_path")
    public BaseResponse<RelationVO> shortestPathToVertex(@RequestBody @Valid PathQuery pathQuery) {
        PathDTO pathDTO = CommandConverter.INSTANCE.toPathDTO(pathQuery);
        RelationVO relationVO = relationManager.shortestPathToVertex(pathDTO);
        return BaseResponse.success(relationVO);
    }
}
