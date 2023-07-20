package com.wt.test.wolverine.interfaces.controller.open;

import com.wt.test.wolverine.app.common.component.response.BaseResponse;
import com.wt.test.wolverine.app.dto.VertexDTO;
import com.wt.test.wolverine.app.dto.VertexMultiDegreePageDTO;
import com.wt.test.wolverine.app.manager.VertexManager;
import com.wt.test.wolverine.interfaces.converter.CommandConverter;
import com.wt.test.wolverine.interfaces.dto.req.VertexMultiDegreePageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiyu
 * @since 2023/7/20
 */
@RestController
@RequestMapping("/open/api/vertex")
@RequiredArgsConstructor
public class VertexOpenController {
    
    private final VertexManager vertexManager;
    
    /**
     * 多度节点
     */
    @PostMapping("/v1/multi_degree")
    public BaseResponse<List<VertexDTO>> pageMultiDegreeVertex(@RequestBody @Valid VertexMultiDegreePageQuery pageQuery) {
        VertexMultiDegreePageDTO pageDTO = CommandConverter.INSTANCE.toVertexMultiDegreePageDTO(pageQuery);
        List<VertexDTO> vertexDTOList = vertexManager.pageMultiDegreeVertex(pageDTO);
        return BaseResponse.success(vertexDTOList);
    }
    
}
