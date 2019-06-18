package com.housekeeping.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.housekeeping.dao.user.AdminDao;
import com.housekeeping.dao.user.UserDao;
import com.housekeeping.dao.user.UserLoginDao;
import com.housekeeping.dao.user.dataobject.AdminDO;
import com.housekeeping.dao.user.dataobject.BindingDO;
import com.housekeeping.dao.user.dataobject.LoginDO;
import com.housekeeping.dao.user.dataobject.UserDO;
import com.housekeeping.dao.user.dataobject.UserLoginDO;
import com.housekeeping.dao.user.dataobject.UserLoginPO;
import com.housekeeping.service.base.ResponseCode;
import com.housekeeping.service.base.ResponseDO;
import com.housekeeping.service.user.UserLoginService;
import com.housekeeping.utils.Md5Utils;
import com.housekeeping.utils.TokenUtils;


@Component("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public ResponseDO<UserLoginDO> getUserLogin(String unionId) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<UserLoginDO>();
		UserLoginDO userLoginDO = userLoginDao.queryUserLogin(unionId);
		if(userLoginDO != null) {
			responseDO.setDataResult(userLoginDO);
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("该用户不存在!");
		}
		return responseDO;
	}
	
	@Override
	public ResponseDO<UserLoginDO> userLogin(LoginDO wxUser) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<>();
		UserDO user = userDao.seletUser(wxUser.getOpenId());
		if (user != null) {
			//已绑定
			UserLoginDO userLoginDO = userLoginDao.seletUserByOpenId(wxUser.getOpenId());
			if (userLoginDO == null) {
				userLoginDO.setOpenId(wxUser.getOpenId());
				userLoginDO.setLoginAccount(wxUser.getNickName());
				userLoginDO.setHeadimgurl(wxUser.getHeadimgurl());
				userLoginDO.setSex(wxUser.getSex());
				userLoginDO.setAccessToken(TokenUtils.creatAesStr(user.getSchoolNum()));
				userLoginDO.setIsBound(Integer.valueOf(1));
				userLoginDO.setUnionId(wxUser.getUnionId());
				userLoginDao.insert(userLoginDO);
			} else {
				userLoginDO.setOpenId(wxUser.getOpenId());
				userLoginDO.setLoginAccount(wxUser.getNickName());
				userLoginDO.setHeadimgurl(wxUser.getHeadimgurl());
				userLoginDO.setSex(wxUser.getSex());
				userLoginDO.setAccessToken(TokenUtils.creatAesStr(user.getSchoolNum()));
				userLoginDO.setIsBound(Integer.valueOf(1));
				userLoginDO.setUnionId(wxUser.getUnionId());
				userLoginDao.update(userLoginDO);
				user.setUserName(wxUser.getNickName());
				user.setHeadimgurl(wxUser.getHeadimgurl());
				user.setSex(wxUser.getSex());
				userDao.updateUser(user);
			}
			responseDO.setDataResult(userLoginDO);
		} else {
			UserLoginDO unbound = new UserLoginDO();
			unbound.setOpenId(wxUser.getOpenId());
			unbound.setAccessToken(wxUser.getOpenId());
			unbound.setLoginAccount(wxUser.getNickName());
			unbound.setHeadimgurl(wxUser.getHeadimgurl());
			unbound.setSex(wxUser.getSex());
			unbound.setUnionId(wxUser.getUnionId());
			unbound.setIsBound(Integer.valueOf(1));
			userLoginDao.insert(unbound);
			responseDO.setMessage(" ");
			responseDO.setDataResult(unbound);
		}
		return responseDO;
	}


	@Override
	public ResponseDO<String> userBinding(BindingDO binding) {
		ResponseDO<String> responseDO = new ResponseDO<String>();
		String isBinding = userDao.seletIsBinding(binding.getUnionId());
		if(isBinding == null) {
			userDao.updateBinding(binding);
			responseDO.setDataResult(isBinding);
			return responseDO;
		}
		responseDO.setCode(ResponseCode.ERROR);
		return responseDO;
	}

	@Override
	public ResponseDO<AdminDO> adminLogin(AdminDO admin) {
		ResponseDO<AdminDO> responseDO = new ResponseDO<AdminDO>();
		AdminDO adminUser = adminDao.getAdmin(admin.getAdminUser());
		if(adminUser.getPassword().equals(admin.getPassword())) {
			responseDO.setDataResult(adminUser);
			return responseDO;
		}
		responseDO.setCode(ResponseCode.ERROR);
		return responseDO;
	}


	
}
