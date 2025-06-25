package org.wx.config;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
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

    @ExceptionHandler({FeignClientException.class})
    @ResponseBody
    public Result handlerFeignClientException(FeignClientException e) {
//        Tracer.trace(e);
        Result result = new Result();
        result.setErrorCode("200");
        result.setErrorMsg("feign出问题了");
        result.setStatus("error");
        return result;
    }

    /**
     * 异常处理后是不会被记录为接口的异常的
     * @param e
     * @return
     */
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

    @ExceptionHandler({DegradeException.class})
    @ResponseBody
    public Result handlerDegradeException(DegradeException e) {
//        Tracer.trace(e);
        Result result = new Result();
        result.setErrorCode("200");
        result.setErrorMsg("熔断中！！！！！！！！");
        result.setStatus("error");
        return result;
    }

}
