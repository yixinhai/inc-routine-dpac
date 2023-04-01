package com.xh.routine.dpac.handler;

import com.xh.routine.dpac.base.BaseResult;
import com.xh.routine.dpac.enums.ResultCodeEnum;
import com.xh.routine.dpac.exception.DpacUserInfoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class DpacGlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DpacGlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = DpacUserInfoNotFoundException.class)
    public BaseResult userInfoNotFoundException(HttpServletRequest request, Exception e) {
        log.error("未从redis中找到用户信息, exception:[{}]", e);
        return BaseResult.fail(ResultCodeEnum.USER_NOT_FOUND.getCode(), "登录超时，请重新登录");
    }
}
