package com.wt.test.wolverine.infra.graph.component;

import org.nebula.contrib.ngbatis.PkGenerator;
import org.springframework.stereotype.Component;

/**
 * @author qiyu
 * @since 2023/7/6
 */
@Component
public class VertexPkGenerator implements PkGenerator {
    
    /**
     * @param tagName
     * @param pkType
     * @param <T>
     * @return
     */
    @Override
    public <T> T generate(String tagName, Class<T> pkType) {
        Long currTime = System.currentTimeMillis();
        if (pkType == String.class) {
            return (T) (tagName + "_" + currTime);
        }
        return (T) currTime;
    }
}
