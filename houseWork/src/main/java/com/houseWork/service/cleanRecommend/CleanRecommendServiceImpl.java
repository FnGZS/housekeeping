package com.houseWork.service.cleanRecommend;

import com.houseWork.dao.cleanRecommend.CleanRecommendMapper;
import com.houseWork.entity.cleanRecommend.CleanRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CleanRecommendServiceImpl implements CleanRecommendService{

    @Autowired
    private CleanRecommendMapper cleanRecommendMapper;

    @Override
    public CleanRecommend add(CleanRecommend cleanRecommend) {
        cleanRecommendMapper.insert(cleanRecommend);
        return cleanRecommend;
    }

    @Override
    public void del(Integer id) {
        cleanRecommendMapper.deleteById(id);
    }

    @Override
    public CleanRecommend update(CleanRecommend cleanRecommend) {
        cleanRecommendMapper.updateByPrimaryKey(cleanRecommend);
        return cleanRecommend;
    }

    @Override
    public List<CleanRecommend> getList(Map map) {
        return cleanRecommendMapper.getCleanRecommendList(map);
    }
}
