package com.houseWork.dao.dict;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DictProvider {

    public String selectByMap(Map<String, Object> params){
        String sql = new SQL(){
            {   SELECT("*");
                FROM("dict");
//                if (StringUtils.isNotEmpty(params.get("type").toString())) {
//                    WHERE("type=#{type} ");
//                }
//
//                if (StringUtils.isNotEmpty(params.get("key").toString())) {
//                    WHERE("key=#{key} ");
//                }

                ORDER_BY("id");}
        }.toString();
        return sql;
    }
}
