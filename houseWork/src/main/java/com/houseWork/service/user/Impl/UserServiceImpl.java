package com.houseWork.service.user.Impl;

import com.houseWork.dao.user.UserDao;
import com.houseWork.entity.user.User;
import com.houseWork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByname(String username) { return userDao.findByName(username); }

    @Override
    public void addUser(User user) {
        User us = userDao.selectByOpenId(user.getOpenId());
        if(us!=null){
            Map map = new HashMap();
            map.put("id",us.getId());
            map.put("openId",user.getOpenId());
            map.put("username",user.getUsername());
            map.put("password",user.getPassword());
            map.put("telephone",user.getTelephone());
            map.put("image",user.getImage());
            userDao.update(map);
        }else {
            user.setRole("USER");
            user.setCreatTime(new Date());
            user.setBalance(0);
            user.setPassword("123456");
            userDao.insert(user);
        }
    }

    @Override
    public List<User> selectByMap(Map map) { return userDao.selectByMap(map); }

    @Override
    public void updateUser(Map map) { userDao.update(map);}

    @Override
    public User selectById(Integer id) { return userDao.selectByPrimaryKey(id);}

}
