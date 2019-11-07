package com.houseWork.dao.user;

import com.houseWork.entity.user.User;
import org.apache.ibatis.annotations.*;
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

    @Select("SELECT u.*,ucm.cid AS select_cleaner FROM user AS u LEFT JOIN user_cleaner_mapper AS ucm ON u.id = ucm.uid WHERE u.username = #{username}")
    @Results(id = "userMore", value = {
        @Result(column = "select_cleaner", property = "cleaner", one = @One(select = "com.houseWork.dao.cleaner.CleanerDao.loadCleanerById") )
    })
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
    @Select("SELECT u.*,ucm.cid AS select_cleaner FROM user AS u LEFT JOIN user_cleaner_mapper AS ucm ON u.id = ucm.uid WHERE u.open_id = #{openId}")
    @ResultMap("userMore")
    User selectByOpenId(String openId);
}
