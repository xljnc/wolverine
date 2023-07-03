package com.wt.test.wolverine.interfaces.controller.admin;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.RelationshipDTO;
import com.wt.test.wolverine.app.manager.RelationshipManager;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.RelationshipCreateCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 关系类型 接口
 *
 * @author qiyu
 * @since 2023/6/26
 */
@RestController
@RequestMapping("/inner/api/relationship")
@RequiredArgsConstructor
public class RelationshipInnerController {
    
    private final RelationshipManager relationshipManager;
    
    @PostMapping("/v1/create")
    public BaseResponse<String> createRelationship(@RequestBody @Valid RelationshipCreateCommand createCommand) {
        RelationshipDTO relationshipDTO = CommandConverter.INSTANCE.toRelationshipDTO(createCommand);
        return BaseResponse.success(relationshipManager.createRelationship(relationshipDTO));
    }
    
    @GetMapping("/v1/delete")
    public BaseResponse<Boolean> deleteRelationship(@RequestParam @NotBlank(message = "关系类型code不能为空") String relationshipCode) {
        return BaseResponse.success(relationshipManager.deleteRelationship(relationshipCode));
    }
    
    @GetMapping("/v1/get")
    public BaseResponse<RelationshipDTO> getRelationship(@RequestParam @NotBlank(message = "关系类型code不能为空") String relationshipCode) {
        return BaseResponse.success(relationshipManager.getRelationship(relationshipCode));
    }
}
