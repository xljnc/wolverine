package com.wt.test.wolverine.app.util;

import lombok.experimental.UtilityClass;

/**
 * @author qiyu
 * @since 2023/7/10
 */
@UtilityClass
public class VertexUtil {
    
    private static final String VERTEX_ID_SEPARATOR = "^";
    
    /**
     * 获取节点id
     *
     * @param tagName tag
     * @param bizId   业务ID
     * @return 节点id
     */
    public static String createVertexId(String tagName, String bizId) {
        return tagName + VERTEX_ID_SEPARATOR + bizId;
    }
    
    /**
     * 获取业务ID
     *
     * @param vertexId 节点id
     * @return 业务ID
     */
    public static String getBizId(String vertexId) {
        int separatorIndex = vertexId.indexOf(VERTEX_ID_SEPARATOR);
        return vertexId.substring(separatorIndex + 1);
    }
    
    /**
     * 获取业务类型
     *
     * @param vertexId 节点id
     * @return 业务类型
     */
    public static String getBizType(String vertexId) {
        int separatorIndex = vertexId.indexOf(VERTEX_ID_SEPARATOR);
        return vertexId.substring(0, separatorIndex);
    }
}
