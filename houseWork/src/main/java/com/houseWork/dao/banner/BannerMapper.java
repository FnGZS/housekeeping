package com.houseWork.dao.banner;


import com.houseWork.entity.banner.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface BannerMapper extends Mapper< Banner >, MySqlMapper<Banner> {

    @Delete("DELETE FROM news WHERE id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = BannerProvider.class, method = "selectByMap")
    List<Banner> getBannerList(Map map);
}
