package com.wt.test.wolverine.interfaces.controller.open;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.RelationDTO;
import com.wt.test.wolverine.app.manager.RelationManager;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.RelationCreateCommand;
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
        RelationDTO relationDTO = CommandConverter.INSTANCE.toRelationDTO(createCommand);
        relationManager.createRelation(relationDTO);
        return BaseResponse.success(Boolean.TRUE);
    }
}
