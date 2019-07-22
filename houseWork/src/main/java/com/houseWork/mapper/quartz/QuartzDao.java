package com.houseWork.mapper.quartz;

import org.apache.ibatis.annotations.Mapper;
import com.houseWork.entity.QuartzJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuartzDao {

    List<QuartzJob> getJob();
}
