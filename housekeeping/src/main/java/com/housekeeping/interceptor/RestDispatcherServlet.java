package com.housekeeping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.housekeeping.controller.base.ErrorModel;
import com.housekeeping.controller.base.RestModelView;
import com.housekeeping.model.enums.BizStatusEnum;
import com.housekeeping.utils.RestLogUtils;

/**
 * @Type RestDispatcherServlet
 * @Desc 重写spring的DispatcherServlet捕获异常方法,添加json串转对象抛出异常时直接捕�?,不走response.
 *       sendError方式输出错误http错误信息
 */
public class RestDispatcherServlet extends DispatcherServlet {
	
	private static final long serialVersionUID = -1627298533964485113L;
	private static final Logger log = LoggerFactory.getLogger(RestDispatcherServlet.class);

    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        if (ex instanceof HttpMessageNotReadableException) {
            ErrorModel error = new ErrorModel();
            error.setMessage("param convert error");
            error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
            RestLogUtils.writeExceptionResolverByError(request, ex, log);
            return new RestModelView(error);
        }
        return super.processHandlerException(request, response, handler, ex);
    }
}
