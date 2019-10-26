package com.houseWork.dao.appointment;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class AppointmentProvider {
    public static String select(Map<String, Object> param) {
        String sql = new SQL() {
            {
                SELECT("*, cleaner_id AS select_cleaner, id AS select_appointment");
                FROM("appointment");
                if (param.containsKey("billingType") && StringUtils.isNotEmpty(param.get("billingType").toString())) {
                    WHERE(" billing_type = #{billingType} ");
                }
                if (param.containsKey("name") && StringUtils.isNotEmpty(param.get("name").toString())) {
                    WHERE(" name LIKE '%' || #{name} || '%' ");
                }
                if (param.containsKey("cleanerId") && StringUtils.isNotEmpty(param.get("cleanerId").toString())) {
                    WHERE(" cleaner_id = #{cleanerId} ");
                }
            }
        }.toString();
        return sql;
    }
}
