package com.houseWork.mapper.user;

import com.houseWork.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <pre>
 * 用户 mapper
 * </pre>
 *
 * @author zjw
 * @date 2019/6/25 16.34
 */
@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserDao extends Mapper<User>, MySqlMapper<User> {

    User findByName(@Param("username") String username);

    int insert(User user);

    void update();
}
