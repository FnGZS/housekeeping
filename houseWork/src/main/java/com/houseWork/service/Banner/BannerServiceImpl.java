package com.houseWork.service.Banner;

import com.houseWork.dao.banner.BannerMapper;
import com.houseWork.entity.banner.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Banner add(Banner banner) {
        bannerMapper.insert(banner);
        return banner;
    }

    @Override
    public void del(Integer id) {
        bannerMapper.deleteById(id);
    }

    @Override
    public Banner update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
        return banner;
    }

    @Override
    public List<Banner> getList(Map map) {
        return bannerMapper.getBannerList(map);
    }
}
