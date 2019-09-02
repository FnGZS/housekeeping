package com.houseWork.dao.test;


import com.houseWork.entity.test.Test;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface TestDao extends Mapper<Test>, MySqlMapper<Test> {

    List<Test> selectByMap(Map<String, Object> params);
}
