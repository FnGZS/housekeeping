package com.housekeeping.interceptor;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 */
public class RestObjectMapper extends ObjectMapper {
    public RestObjectMapper() {
        super();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属�?
        disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
