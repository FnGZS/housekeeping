package com.houseWork.service.cleanRecommend;

import com.houseWork.entity.cleanRecommend.CleanRecommend;

import java.util.List;
import java.util.Map;

public interface CleanRecommendService {
    CleanRecommend add(CleanRecommend cleanRecommend);

    void del(Integer id);

    CleanRecommend update(CleanRecommend cleanRecommend);

    List< CleanRecommend > getList(Map map);
}
