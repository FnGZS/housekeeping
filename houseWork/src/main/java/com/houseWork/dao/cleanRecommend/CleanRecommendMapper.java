package com.houseWork.dao.cleanRecommend;


import com.houseWork.entity.banner.Banner;
import com.houseWork.entity.cleanRecommend.CleanRecommend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CleanRecommendMapper extends Mapper< CleanRecommend >, MySqlMapper<Banner> {

    @Delete("DELETE FROM news WHERE id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = CleanRecommendProvider.class, method = "selectByMap")
    List< CleanRecommend > getCleanRecommendList(Map map);
}
