package com.houseWork.dao.test;


import com.houseWork.entity.Test.Test;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface TestDao extends Mapper<Test>, MySqlMapper<Test> {
}
