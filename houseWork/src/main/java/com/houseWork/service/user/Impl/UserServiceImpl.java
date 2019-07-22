package com.houseWork.service.user.Impl;

import com.houseWork.entity.QuartzJob;
import com.houseWork.mapper.quartz.QuartzDao;
import com.houseWork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseWork.mapper.user.UserDao;
import com.houseWork.entity.User;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private QuartzDao quartzDao;

    @Override
    public User findByname(String username) { User userinfo = userDao.findByName(username);return userinfo; }

    @Override
    public int insertUser(String username, String password) { return userDao.insert(username, password); }

    @Override
    public List<QuartzJob> findAll() {
        return quartzDao.getJob();
    }

    @Override
    public void update() {
        userDao.update();
    }

}
