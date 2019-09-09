package com.houseWork.dao.dict;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DictProvider {

    public static String selectByMap(Map<String, Object> params){
        String sql = new SQL(){
            {   SELECT("*");
                FROM("dict");
                if (params.containsKey("type") && StringUtils.isNotEmpty(params.get("type").toString())) {
                    WHERE(" type = #{type}");
                }
                if (params.containsKey("key")&& StringUtils.isNotEmpty(params.get("key").toString())) {
                    WHERE(" k = #{key}");
                }
                ORDER_BY("id DESC");
            }
        }.toString();
        return sql;
    }
}
