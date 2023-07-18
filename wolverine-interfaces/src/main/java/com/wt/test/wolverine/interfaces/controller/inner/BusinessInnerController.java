package com.wt.test.wolverine.interfaces.controller.inner;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.BusinessDTO;
import com.wt.test.wolverine.app.manager.BusinessManager;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.BusinessCreateCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 业务类型 接口
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
    public BaseResponse<Long> createBusiness(@RequestBody @Valid BusinessCreateCommand createCommand) {
        BusinessDTO businessDTO = CommandConverter.INSTANCE.toBusinessDTO(createCommand);
        return BaseResponse.success(businessManager.createBusiness(businessDTO));
    }
    
    @GetMapping("/v1/get")
    public BaseResponse<BusinessDTO> getBusiness(@RequestParam @NotBlank(message = "业务类型type不能为空") String businessType) {
        return BaseResponse.success(businessManager.getBusiness(businessType));
    }
    
    @GetMapping("/v1/delete")
    public BaseResponse<Boolean> deleteBusiness(@RequestParam @NotBlank(message = "业务类型type不能为空") String businessType) {
        return BaseResponse.success(businessManager.deleteBusiness(businessType));
    }
}
