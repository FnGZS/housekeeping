package com.houseWork.service.user.Impl;

import com.houseWork.entity.User;
import com.houseWork.mapper.user.UserDao;
import com.houseWork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByname(String username) { User userinfo = userDao.findByName(username);return userinfo; }

    @Override
    public void update() {
        userDao.update();
    }

    @Override
    public void addUser(User user) { userDao.insert(user);}

}
