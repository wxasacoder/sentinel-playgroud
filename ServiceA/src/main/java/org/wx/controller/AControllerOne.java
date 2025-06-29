package org.wx.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.ServiceBfeign.ServiceBFeign;
//import org.wx.config.MyUrlBlockHandler;

/**
 * @author wuxin
 * @date 2024/03/11 23:55:48
 */
@RestController
public class AControllerOne {

    @GetMapping("/hello-world")
    public String helloImA(){
        return "Hello I am A"
                + serviceBFeign.helloImB()
                ;
    }

    @Resource
    private ServiceBFeign serviceBFeign;


    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(long s) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
//    public static String exceptionHandler(BlockException ex) {
//         Do some log here.
//        ex.printStackTrace();
//        return "Oops, error occurred at " ;
//    }
}
