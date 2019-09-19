package com.houseWork.dao.news;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class NewsProvider {
    public static String selectByMap(Map<String, Object> params){
        String sql = new SQL(){
            {   SELECT("*");
                FROM("news");
                if (params.containsKey("title") && StringUtils.isNotEmpty(params.get("title").toString())) {
                    WHERE(" title LIKE #{title}");
                }
                if (params.containsKey("isRecommend")&& StringUtils.isNotEmpty(params.get("isRecommend").toString())) {
                    WHERE(" isRecommend = #{isRecommend}");
                }
                ORDER_BY("id DESC");
            }
        }.toString();
        return sql;
    }
}
