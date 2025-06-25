package org.wx.config;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author FuQiang.wan
 * @Date 2020/10/29 16:13
 */
@ControllerAdvice
@Slf4j
public class DemeterExceptionHandle {

    @ExceptionHandler({FeignException.class})
    @ResponseBody
    public Result handler(FeignException e) {
//        Tracer.trace(e);
        Result result = new Result();
        result.setErrorCode("200");
        result.setErrorMsg("系统异常");
        result.setStatus("error");
        return result;
    }
}
