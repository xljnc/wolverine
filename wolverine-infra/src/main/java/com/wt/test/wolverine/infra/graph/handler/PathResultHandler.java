package com.wt.test.wolverine.infra.graph.handler;

import com.vesoft.nebula.client.graph.data.PathWrapper;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.data.ValueWrapper;
import com.wt.test.wolverine.infra.common.util.JsonUtil;
import com.wt.test.wolverine.infra.graph.model.EdgeDO;
import com.wt.test.wolverine.infra.graph.model.PathDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nebula.contrib.ngbatis.handler.AbstractResultHandler;
import org.nebula.contrib.ngbatis.utils.ResultSetUtil;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author qiyu
 * @since 2023/7/21
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class PathResultHandler extends AbstractResultHandler<PathDO, PathDO> {
    
    private static final String PROPERTY_CTIME = "ctime";
    
    private final JsonUtil jsonUtil;
    
    @Override
    public PathDO handle(PathDO newResult, ResultSet result, Class resultType) {
        ResultSet.Record record = result.rowValues(0);
        return handle(newResult, record);
    }
    
    public PathDO handle(PathDO newResult, ResultSet.Record record) {
        PathWrapper pathWrapper = ResultSetUtil.getValue(record.values().get(0));
        pathWrapper.getRelationships().forEach(relationship -> {
            long ctime = 0L;
            try {
                ValueWrapper valueWrapper = relationship.properties().get(PROPERTY_CTIME);
                if (Objects.nonNull(valueWrapper)) {
                    ctime = valueWrapper.asLong();
                }
            } catch (UnsupportedEncodingException e) {
                log.error("获取关系的ctime异常, relationship:{}", jsonUtil.writeValueAsString(relationship), e);
            }
            EdgeDO edgeDO = EdgeDO.builder()
                    .type(relationship.edgeName())
                    .fromVertexId(ResultSetUtil.getValue(relationship.srcId()))
                    .toVertexId(ResultSetUtil.getValue(relationship.dstId()))
                    .createTime(ctime)
                    .build();
            newResult.getEdgeDOList().add(edgeDO);
        });
        return newResult;
    }
}
