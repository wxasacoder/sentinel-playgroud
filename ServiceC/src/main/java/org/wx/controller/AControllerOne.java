package org.wx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.wx.config.MyUrlBlockHandler;

/**
 * @author wuxin
 * @date 2024/03/11 23:55:48
 */
@RestController
public class AControllerOne {

    @GetMapping("/hello-world-2")
    public String helloImA(){
        return "Hello I am b";
    }

}
