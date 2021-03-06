package com.houseWork.service.news;

import com.houseWork.entity.news.JYZParam;
import com.houseWork.entity.news.News;

import java.util.List;
import java.util.Map;

public interface NewsService {
    News add(News news);

    void del(Integer id);

    News update(News news);

    List<News> getList(Map map);

    News getDetail(Integer nid);

    void addjyz(JYZParam param);

    List<JYZParam> getjyz();
}
