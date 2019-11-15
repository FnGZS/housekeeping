package com.houseWork.dao.cleanRecommend;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class CleanRecommendProvider {
    public static String selectByMap(Map<String, Object> params){
        String sql = new SQL(){
            {   SELECT("*");
                FROM("cleanRecommend");
                if (params.containsKey("title") && StringUtils.isNotEmpty(params.get("title").toString())) {
                    WHERE(" title LIKE #{title}");
                }
                ORDER_BY("id DESC");
            }
        }.toString();
        return sql;
    }
}
