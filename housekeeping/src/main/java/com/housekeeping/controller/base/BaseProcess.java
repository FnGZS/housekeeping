package com.housekeeping.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.housekeeping.controller.user.model.UserModel;
import com.housekeeping.model.reqinfo.ReqParam;
import com.housekeeping.utils.IPUtils;

/**
 * @author 
 *
 */
public abstract class BaseProcess {
	
    /**
     * 从request里取得真实ip
     * 
     * @param request
     * @return
     */
    public String getIp() {
        return IPUtils.getIp(getRequest());
    }

    /**
     * 获得用户Id
     * 
     * @return
     */
    public Long getUserId() {
        UserModel user = getUser();
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }

    /**
     * 获得登陆账户
     * 
     * @return
     */
    public String getLoginAccount() {
        UserModel user = getUser();
        if (user == null) {
            return null;
        }
        return user.getLoginAccount();
    }

    /**
     * 获得用户对象
     * 
     * @return
     */
    public UserModel getUser() {
        if (getReqParam() != null && StringUtils.isNotBlank(getReqParam().getUserId())) {
            UserModel user = new UserModel();
            user.setLoginAccount(getReqParam().getLoginAccount());
            user.setUserId(Long.valueOf(getReqParam().getUserId()));
            return user;
        }
        return null;
    }

    /**
     * 获得用户操作系统
     * 
     * @return
     */
    public String getUserOS() {
        if (getReqParam() != null && getReqParam().getReqHead() != null) {
            return getReqParam().getReqHead().getOs();
        }
        return "";
    }
    
    /**
     * 获得APP版本�?
     * 
     * @return
     */
    public String getAppVersion() {
    	if (getReqParam() != null && getReqParam().getReqHead() != null) {
    		return getReqParam().getReqHead().getVersion();
    	}
    	return "";
    }
    
    /**
     * 获得用户浏览器类�?
     * 
     * @return
     */
    public String getUserBrowser() {
    	if (getReqParam() != null && getReqParam().getReqHead() != null) {
            return getReqParam().getReqHead().getBrowser();
        }
        return "";
    }

    /**
     * 获得request对象
     * 
     * @return
     */
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public ReqParam getReqParam() {
        return (ReqParam) getRequest().getAttribute("ReqParam");
    }
    
    public String getPicPath(String ossKey) {
    	return getPicPath(ossKey, null);
    }
    
    public String getPicPath(String ossKey, Integer picHigh) {
    	if(StringUtils.isBlank(ossKey)) {
    		return "";
    	}
    	if(picHigh != null && picHigh.intValue() != 0) {
    		ossKey = ossKey + "?x-oss-process=image/resize,h_" + picHigh;
    	}
    	return null;
    }
    
	// 样品图片列表展示�?大高�?
	public static final int LABLE_TEMPLATE_PIC_MAX_HIGH = 400;
	// 样品图片列表展示�?大高�?
	public static final int SAMPLE_PIC_LIST_MAX_HIGH = 180;
	// 样品图片详情展示�?大高�?
	public static final int SAMPLE_PIC_DETAIL_MAX_HIGH = 1080;
}
