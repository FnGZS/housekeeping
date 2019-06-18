package com.housekeeping.dao.user;

import com.housekeeping.dao.user.dataobject.BindingDO;
import com.housekeeping.dao.user.dataobject.UserDO;

public interface UserDao {

	UserDO seletUser(String openId);

	String seletIsBinding(String openId);

	void updateBinding(BindingDO binding);

	String getName(Long id);

	void updateUser(UserDO user);


}
