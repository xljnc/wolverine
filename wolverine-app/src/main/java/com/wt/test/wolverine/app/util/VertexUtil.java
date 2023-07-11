package com.wt.test.wolverine.app.util;

import lombok.experimental.UtilityClass;

/**
 * @author qiyu
 * @since 2023/7/10
 */
@UtilityClass
public class VertexUtil {
    
    private static final String VERTEX_ID_SEPARATOR = "^";
    
    public static String getVertex(String tagName, String vertexId) {
        return tagName + VERTEX_ID_SEPARATOR + vertexId;
    }
}
