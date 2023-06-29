package com.wt.test.wolverine.controller.admin;

import com.wt.test.wolverine.common.component.response.BaseResponse;
import com.wt.test.wolverine.converter.CommandConverter;
import com.wt.test.wolverine.dto.BusinessDTO;
import com.wt.test.wolverine.dto.req.BusinessCreateCommand;
import com.wt.test.wolverine.manager.BusinessManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 业务类型接口
 *
 * @author qiyu
 * @since 2023/6/26
 */
@RestController
@RequestMapping("/inner/api/business")
@RequiredArgsConstructor
public class BusinessInnerController {
    
    private final BusinessManager businessManager;
    
    @PostMapping("/v1/create")
    public BaseResponse<String> createBusiness(@RequestBody @Valid BusinessCreateCommand createCommand) {
        BusinessDTO businessDTO = CommandConverter.INSTANCE.toBusinessDTO(createCommand);
        return BaseResponse.success(businessManager.createBusiness(businessDTO));
    }
    
    @GetMapping("/v1/delete")
    public BaseResponse<Boolean> deleteBusiness(@RequestParam @NotBlank(message = "业务类型code不能为空") String businessCode) {
        return BaseResponse.success(businessManager.deleteBusiness(businessCode));
    }
}
