package com.houseWork.dao.news;


import com.houseWork.entity.news.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsMapper extends Mapper<News>, MySqlMapper<News> {

    @Delete("DELETE FROM news WHERE nid = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = NewsProvider.class, method = "selectByMap")
    List<News> getNewsList(Map map);
}
