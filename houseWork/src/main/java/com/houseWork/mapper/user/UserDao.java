package com.houseWork.mapper.user;

import com.houseWork.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User findByName(@Param("username") String username);

    int insert(@Param("username") String name, @Param("password") String password);

    void update();
}
