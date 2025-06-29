package org.wx.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 自定义错误页面
 *
 * @author Saint
 */
 @Component
public class MyUrlBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        ErrorMsg msg = null;
        if (e instanceof FlowException) {
            msg = ErrorMsg.builder()
                    .msg("请求太快了, 慢下来吧！")
                    .status(101).build();
        } else if (e instanceof DegradeException) {
            msg = ErrorMsg.builder()
                    .msg("呀，我被降级了！")
                    .status(102).build();
        } else if (e instanceof ParamFlowException) {
            msg = ErrorMsg.builder()
                    .msg("呀，我被热点参数限流了！")
                    .status(103).build();
        } else if (e instanceof SystemBlockException) {
            msg = ErrorMsg.builder()
                    .msg("呀，系统规则（负载/...不满足要求）！")
                    .status(104).build();
        } else if (e instanceof AuthorityException) {
            msg = ErrorMsg.builder()
                    .msg("呀，授权规则不通过！")
                    .status(105).build();
        }

        // http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // spring mvc自带的json操作工具jackson
        new ObjectMapper()
                .writeValue(
                        httpServletResponse.getWriter(),
                        msg
                );
    }
}

@Data
@Builder
class ErrorMsg {
    private Integer status;

    private String msg;
}