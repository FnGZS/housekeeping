package com.houseWork.dao.news;


import com.houseWork.entity.news.JYZParam;
import com.houseWork.entity.news.News;
import org.apache.ibatis.annotations.*;
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

    @Select("SELECT * FROM news WHERE nid = #{nid}")
    News getDetail(@Param("nid") Integer nid);

    @Insert(" INSERT INTO jyz(name,area,areaname,address,brandname,type,discount,exhaust,position,lat,lon,price,gastrprice,fwlsmc)" +
            " VALUES(#{name},#{area},#{areaname},#{address},#{brandname},#{type},#{discount},#{exhaust},#{position},#{lat},#{lon},#{price},#{gastrprice},#{fwlsmc})")
    void addjyz(JYZParam param);

    @Select("SELECT * FROM jyz")
    List<JYZParam> getjyz();
}
