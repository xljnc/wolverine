package com.wt.test.wolverine.infra.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author yipin
 * @date 2020/11/5
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JsonUtil {
    
    private final ObjectMapper objectMapper;
    
    public String writeValueAsString(Object value) throws RuntimeException {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            String msg = String.format("Jackson转String失败,对象:%s", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
    
    public byte[] writeValueAsBytes(Object value) throws RuntimeException {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            String msg = String.format("Jackson转String失败,对象:%s", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
    
    public <T> T readValue(String content, Class<T> valueType) throws RuntimeException {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            String msg = String.format("Jackson转换对象失败,String:%s,Class:%s", content, valueType.getCanonicalName());
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
    
    public <T> T readValue(byte[] content, Class<T> valueType) throws RuntimeException {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            String msg = String.format("Jackson转换对象失败,String:%s,Class:%s", new String(content, StandardCharsets.UTF_8), valueType.getCanonicalName());
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
        
    }
    
    public JsonNode readValue(String content) throws RuntimeException {
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            String msg = String.format("Jackson转换对象失败,String:%s", content);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
    
    public JsonNode readValue(byte[] content) throws RuntimeException {
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            String msg = String.format("Jackson转换对象失败,String:%s", new String(content, StandardCharsets.UTF_8));
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
    
}
