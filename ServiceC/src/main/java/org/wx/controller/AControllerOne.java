package org.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.Result;
//import org.wx.config.MyUrlBlockHandler;

/**
 * @author wuxin
 * @date 2024/03/11 23:55:48
 */
@RestController
@Slf4j
public class AControllerOne {

    @GetMapping("/hello-world-2")
    public Result helloImA(){
        log.info("接受请求！！！！");
        return new Result("你好我是B服务！").setStatus("ERROR");
    }

}
