package com.housekeeping.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.housekeeping.controller.base.BaseProcess;
import com.housekeeping.controller.user.model.AdminModel;
import com.housekeeping.controller.user.model.BindingModel;
import com.housekeeping.controller.user.model.LoginModel;
import com.housekeeping.controller.user.param.AdminParam;
import com.housekeeping.controller.user.param.BindingParam;
import com.housekeeping.controller.user.param.LoginParam;
import com.housekeeping.dao.user.UserDao;
import com.housekeeping.dao.user.dataobject.AdminDO;
import com.housekeeping.dao.user.dataobject.BindingDO;
import com.housekeeping.dao.user.dataobject.LoginDO;
import com.housekeeping.dao.user.dataobject.UserLoginDO;
import com.housekeeping.model.enums.HttpCodeEnum;
import com.housekeeping.service.base.ResponseCode;
import com.housekeeping.service.base.ResponseDO;
import com.housekeeping.service.user.UserLoginService;
import com.housekeeping.service.user.dataobject.UserInfo;
import com.housekeeping.service.weixin.WeixinAppService;
import com.housekeeping.utils.Md5Utils;
import com.housekeeping.utils.TokenUtils;

import org.apache.commons.lang3.StringUtils;

@Component
public class UserLoginProcess extends BaseProcess{

	@Autowired
	private UserLoginService userLoginService;


	public LoginModel doLogin(LoginParam param) {
		LoginModel model = new LoginModel();
		String openId = null;

		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		ResponseDO<UserInfo> userInfoResult;
		if ((platUserInfoMap != null) && (!platUserInfoMap.isEmpty())) {
			if (StringUtils.isBlank(platUserInfoMap.get("encryptedData"))
					|| StringUtils.isBlank(platUserInfoMap.get("iv"))) {
				model.setCode(ResponseCode.ERROR);
				model.setMessage("微信小程序登录异常，缺少必要参数");
				return model;
			}
			userInfoResult = WeixinAppService.getUserInfo(param.getPlatCode(), platUserInfoMap);
		} else {
			userInfoResult = null;
		}
		if (!userInfoResult.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(userInfoResult.getMessage());
			return model;
		}
		UserInfo userInfo = userInfoResult.getDataResult();
		try {
			openId = Md5Utils.getMD5(userInfo.getOpenId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nickName = userInfo.getNickName();
		String headimgurl = userInfo.getHeadimgurl();
		Integer sex = userInfo.getSex();
		String unionId = userInfo.getUnionId();
		LoginDO wxUser = new LoginDO();
		wxUser.setHeadimgurl(headimgurl);
		wxUser.setNickName(nickName);
		wxUser.setOpenId(openId);
		wxUser.setSex(sex);
		wxUser.setUnionId(unionId);
		ResponseDO<UserLoginDO> responseDO = userLoginService.userLogin(wxUser);
		UserLoginDO login = responseDO.getDataResult();
		model.setAuthorization(login.getAccessToken());
		model.setSex(sex);
		model.setAvatar(headimgurl);
		model.setUserName(nickName);
		model.setSessionKey(userInfo.getSessionKey());
		model.setOpenAccount(login.getLoginAccount());
		model.setPhone(login.getTelephone());
		model.setIsbound(login.getIsBound());
		model.setOpenId(userInfo.getOpenId());
		if (model.getIsbound() == 1) {
			try {
				model.setUserId(TokenUtils.getIdFromAesStr(login.getAccessToken()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.setUserId(null);
		}
		return model;
	}

	public BindingModel binding(BindingParam param) {
		BindingModel model = new BindingModel();
		BindingDO binding = new BindingDO();
		if(param!=null) {
			binding.setUnionId(param.getUnionId());;
			binding.setSchoolNum(param.getSchoolNum());;
			binding.setPassword(param.getPassword());
			ResponseDO<String> responseDO = userLoginService.userBinding(binding);
			model.setResult(responseDO.getDataResult());
			model.setCode(responseDO.getCode());
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		return model;
	}

	public AdminModel adminLogin(AdminParam param) {
		AdminModel model = new AdminModel();
		AdminDO admin = new AdminDO();
		admin.setAdminUser(param.getAdminUser());
		admin.setPassword(param.getPassword());
		ResponseDO<AdminDO> response = userLoginService.adminLogin(admin);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setAdminName(response.getDataResult().getAdminName());
		model.setAdminUser(response.getDataResult().getAdminUser());
		model.setMessage(response.getMessage());
		return model;
	}





}
