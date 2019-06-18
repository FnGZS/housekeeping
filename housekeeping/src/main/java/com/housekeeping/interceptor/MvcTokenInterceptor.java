package com.housekeeping.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.housekeeping.dao.user.dataobject.UserLoginDO;
import com.housekeeping.exception.CertificateException;
import com.housekeeping.model.constant.UrlRightConstant;
import com.housekeeping.model.reqinfo.ReqHead;
import com.housekeeping.model.reqinfo.ReqParam;
import com.housekeeping.service.base.ResponseDO;
import com.housekeeping.service.user.UserLoginService;
import com.housekeeping.utils.DeviceUtils;
import com.housekeeping.utils.IPUtils;
import com.housekeeping.utils.TokenUtils;

import eu.bitwalker.useragentutils.UserAgent;


public class MvcTokenInterceptor implements HandlerInterceptor {

	private final String ACCESS_TOKEN = "authorization";
	private final String APP_VERSION = "app-version";
	private final String OS = "os";
	private final String OS_VERSION = "os-version";
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
		request.setAttribute("req_time", new Date());
		// 设置request属�??,先设置属性，以免日志没打�?
		String url = request.getRequestURI();
		
		ReqHead reqHead = new ReqHead();
		reqHead.setAccessToken(request.getHeader(ACCESS_TOKEN));
		if(StringUtils.isNotBlank(request.getHeader(APP_VERSION))) { // 手机APP
			reqHead.setVersion(request.getHeader(APP_VERSION));
			reqHead.setOs(request.getHeader(OS) + "_APP");
			reqHead.setOsVersion(request.getHeader(OS_VERSION));
		} else {
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			reqHead.setBrowser(userAgent.getBrowser().getName());
			reqHead.setOs(userAgent.getOperatingSystem().getName());
		}
		
		ReqParam reqParam = new ReqParam();
		reqParam.setReqHead(reqHead);
		reqParam.setIp(IPUtils.getIp(request));
		reqParam.setUrl(url);
		request.setAttribute("ReqParam", reqParam);
		
		// 忽略登陆
		for (String ignoreLoginUrl : UrlRightConstant.notVerifyUrlList) {
			if (url.indexOf(ignoreLoginUrl) != -1) {
				return true;
			}
		}
		
		// 安全认证
		checkAccessToken(reqParam);
		return true;
	}
	
	/**
	 * 安全认证
	 * 
	 * @param request
	 * @throws CertificateException
	 */
	private void checkAccessToken(ReqParam reqParam) throws Exception {
		String url = reqParam.getUrl().replaceAll("\\d+",""); // 去掉url中的数字
		String accessToken = reqParam.getReqHead().getAccessToken();
		if (StringUtils.isBlank(accessToken)) {
			// 非必须登录的接口，access_token为空直接返回
			for (String loginNotNecessaryUrl : UrlRightConstant.loginNotNecessaryUrlList) {
				if (url.indexOf(loginNotNecessaryUrl) != -1) {
					return ;
				}
			}
			// 安全认证，需要登录的接口，必须有access_token
			for (String secureUrl : UrlRightConstant.loginVerifyUrlList) {
				if (url.indexOf(secureUrl) != -1) {
					throw new CertificateException("请先登录");
				}
			}
		} else {
			// 验证ip和浏览器，判断是否是在其他地方登录过导致的token失效，还可以根据IP获取地区，判断是否是异地登录（需要设计常用地�?�?
			// 当token更新时间在本次操作前X时间内，提示当前账号已在其他终端登录，如果ip为异地，警告异地登录
			// 否则超过X时间，只提示token失效。如果ip为异地，告知，该账号曾在异地登录过�??
			try {
				
				if(accessToken == null) {
					throw new CertificateException("登录信息无效！请重新登录");
				}
				ResponseDO<UserLoginDO> responeDO = userLoginService.getUserLogin(accessToken);
				//ResponseDO<UserLoginDO> responeDO = userLoginService.getUserLogin(userId, DeviceUtils.getDeviceTypeFromOS(reqParam.getReqHead().getOs()));
				if(!responeDO.isSuccess() || responeDO.getDataResult() == null) {
					throw new CertificateException("登录信息无效！请重新登录");
				}
				UserLoginDO userLoginDO = responeDO.getDataResult();
				if(userLoginDO.getIsBound().intValue() == 1) {
					throw new CertificateException("无权限，请绑�?");
				}
				// 验证token是否失效—�?��?��?�token已失效，请重新登�?
				/*
				 * if(userLoginDO.getIsBound().intValue() == 1) {
					throw new CertificateException("登录信息已失效！请重新登�?");
				}
				*/
				String lastAccessToken = userLoginDO.getAccessToken();
				if(StringUtils.isBlank(lastAccessToken) || !accessToken.equals(lastAccessToken)) {
					throw new CertificateException("登录信息无效！请重新登录");
				}
				//reqParam.setUserId(userLoginDO.getUserId() + "");
				reqParam.setLoginAccount(userLoginDO.getLoginAccount());
				return ;
			} catch (Exception e) {
				throw new CertificateException("登录信息无效！请重新登录");
			}
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView model)
			throws Exception {
	}
}
