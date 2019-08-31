package com.houseWork.dao.user;

import com.houseWork.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 用户 mapper
 * </pre>
 *
 * @author zjw
 * @date 2019/6/25 16.34
 */
@Repository
public interface UserDao extends Mapper<User>, MySqlMapper<User> {

    User findByName(@Param("username") String username);

    /**
     * 修改用户
     */
    void update(Map map);

    /**
     * 查找用户
     */
    List<User> selectByMap(Map map);

    /**
     * 查找byOpenId
     */
    User selectByOpenId(String openId);
}
