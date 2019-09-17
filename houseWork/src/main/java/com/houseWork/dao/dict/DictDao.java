package com.houseWork.dao.dict;

import com.houseWork.entity.dict.DictEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface DictDao extends Mapper<DictEntity>, MySqlMapper<DictEntity> {

    @SelectProvider(type = DictProvider.class, method = "selectByMap")
    List<DictEntity> selectByMap(Map<String, Object> params);

    @Select("SELECT * FROM dict WHERE type = #{type} AND k = #{k}")
    DictEntity getDetail(@Param("type") String type, @Param("k") String k);
}
