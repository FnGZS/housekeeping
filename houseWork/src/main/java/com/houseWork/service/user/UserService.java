package com.houseWork.service.user;

import com.houseWork.entity.QuartzJob;
import com.houseWork.entity.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {

    @Cacheable(value = "proUserLogin", key = "#username")
    User findByname(String username);

    //增加
    int insertUser(String username, String password);

    List<QuartzJob> findAll();

    void update();
}
