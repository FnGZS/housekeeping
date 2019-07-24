package com.houseWork.service.user;

import com.houseWork.entity.User;

public interface UserService {



    User findByname(String username);

    void update();

    /**
     * 添加用户
     * @params [user]
     * @return void
     * @date 2019/7/23 19:34
     */
    void addUser(User user);
}
