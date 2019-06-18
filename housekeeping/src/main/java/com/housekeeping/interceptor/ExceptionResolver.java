package com.housekeeping.interceptor;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.channels.ClosedChannelException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.housekeeping.controller.base.ErrorModel;
import com.housekeeping.controller.base.RestModelView;
import com.housekeeping.exception.AuthorityException;
import com.housekeeping.exception.CertificateException;
import com.housekeeping.exception.ForceExitException;
import com.housekeeping.exception.ParamException;
import com.housekeeping.exception.UploadException;
import com.housekeeping.model.enums.BizStatusEnum;
import com.housekeeping.utils.RestLogUtils;

/**
 * @Type ExceptionResolver
 * @Desc 统一异常处理
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ErrorModel error = new ErrorModel();
        // 安全问题
        if (ex instanceof CertificateException) {
            error.setCode(BizStatusEnum.SYS_NOT_PERMISSION.getCodeEnum().getCode());
            if(StringUtils.isNotBlank(ex.getMessage())) {
            	error.setMessage(ex.getMessage());
            } else {
            	error.setMessage(BizStatusEnum.SYS_NOT_PERMISSION.getMessage());
            }
            // RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 其他用户登陆,强制�?�?
        else if (ex instanceof ForceExitException) {
            error.setCode(BizStatusEnum.SYS_OTHER_LOGIN_ERROR.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_OTHER_LOGIN_ERROR.getMessage());
            // RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 参数异常(参数为空)捕捉
        else if (ex instanceof EOFException) {
            error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
            error.setMessage("post参数不能为空");
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 参数异常(类型错误)捕捉
        else if (ex instanceof ParamException) {
            ParamException paramException = (ParamException) ex;
            error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
            error.setMessage(paramException.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 上传异常捕捉
        else if (ex instanceof UploadException) {
        	UploadException uploadException = (UploadException) ex;
            error.setCode(BizStatusEnum.SYS_UPlOAD_EXCEPTION.getCodeEnum().getCode());
            error.setMessage(uploadException.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 权限异常捕捉
        else if (ex instanceof AuthorityException) {
        	AuthorityException authorityException = (AuthorityException) ex;
            error.setCode(BizStatusEnum.SYS_OPERATE_FORBID.getCodeEnum().getCode());
            error.setMessage(authorityException.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }

        else if (ex instanceof ClosedChannelException) {
            error.setCode(BizStatusEnum.SYS_STEAM_ERROR.getCodeEnum().getCode());
            error.setMessage(ex.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // 上传超过�?大尺�?
        else if (ex instanceof MaxUploadSizeExceededException) {
            error.setCode(BizStatusEnum.SYS_MAXUP_lOAD_SIZE.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_MAXUP_lOAD_SIZE.getMessage());
            // RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // IO异常
        else if (ex instanceof IOException) {
            error.setCode(BizStatusEnum.IO_EXCEPTION.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.IO_EXCEPTION.getMessage());
            RestLogUtils.writeExceptionResolverByInfo(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        // Throwable异常
        else if (ex instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException throwable = (UndeclaredThrowableException) ex;
            if (throwable.getUndeclaredThrowable().getClass().equals(ParamException.class)) {
                ParamException paramException = (ParamException) throwable.getUndeclaredThrowable();
                error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
                error.setMessage(paramException.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            }
            // 错误流问�?
            else if (throwable.getUndeclaredThrowable().getClass().equals(ClosedChannelException.class)) {
                ParamException paramException = (ParamException) throwable.getUndeclaredThrowable();
                error.setCode(BizStatusEnum.SYS_PARAM_ERROR.getCodeEnum().getCode());
                error.setMessage(paramException.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            } else {
                error.setCode(BizStatusEnum.SYS_EXCEPTION.getCodeEnum().getCode());
                error.setMessage(BizStatusEnum.SYS_EXCEPTION.getMessage());
                RestLogUtils.writeExceptionResolverByError(request, ex, logger);
                RestLogUtils.writeRestLogByInfo(request, error);
            }
        }
        // 其他异常
        else {
            error.setCode(BizStatusEnum.SYS_EXCEPTION.getCodeEnum().getCode());
            error.setMessage(BizStatusEnum.SYS_EXCEPTION.getMessage());
            RestLogUtils.writeExceptionResolverByError(request, ex, logger);
            RestLogUtils.writeRestLogByInfo(request, error);
        }
        ModelAndView mv = new RestModelView(error);
        return mv;
    }

}
