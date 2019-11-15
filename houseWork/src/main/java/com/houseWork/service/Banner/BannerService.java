package com.houseWork.service.Banner;

import com.houseWork.entity.banner.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    Banner add(Banner banner);

    void del(Integer id);

    Banner update(Banner banner);

    List< Banner > getList(Map map);
}
