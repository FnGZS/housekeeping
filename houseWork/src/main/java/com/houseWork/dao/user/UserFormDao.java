package com.houseWork.dao.user;

import com.houseWork.entity.weixin.UserFormDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFormDao {
    void insertFormId(UserFormDO fromDO);

    UserFormDO getFormId(String openId);

    void deleteFormId(Long id);
}
