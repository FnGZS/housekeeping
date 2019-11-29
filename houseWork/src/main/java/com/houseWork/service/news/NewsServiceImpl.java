package com.houseWork.service.news;

import com.houseWork.dao.news.NewsMapper;
import com.houseWork.entity.news.JYZParam;
import com.houseWork.entity.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public News add(News news) {
        newsMapper.insert(news);
        return news;
    }

    @Override
    public void del(Integer id) {
        newsMapper.deleteById(id);
    }

    @Override
    public News update(News news) {
        newsMapper.updateByPrimaryKey(news);
        return news;
    }

    @Override
    public List<News> getList(Map map) {
        return newsMapper.getNewsList(map);
    }

    @Override
    public News getDetail(Integer nid) {
        return newsMapper.getDetail(nid);
    }

    @Override
    public void addjyz(JYZParam param) {
        newsMapper.addjyz(param);
    }

    @Override
    public List<JYZParam> getjyz() {
        return newsMapper.getjyz();
    }
}
