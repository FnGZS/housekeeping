package com.houseWork.dao.banner;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BannerProvider {
    public static String selectByMap(Map<String, Object> params){
        String sql = new SQL(){
            {   SELECT("*");
                FROM("banner");
                if (params.containsKey("title") && StringUtils.isNotEmpty(params.get("title").toString())) {
                    WHERE(" title LIKE #{title}");
                }
                ORDER_BY("id DESC");
            }
        }.toString();
        return sql;
    }
}
