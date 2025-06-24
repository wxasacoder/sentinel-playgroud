package org.wx.ServiceBfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuxin
 * @date 2025/06/24 14:28:48
 */
@FeignClient(contextId = "ServiceB", value = "ServiceB")
public interface ServiceBFeign {

    @GetMapping("/hello-world-2")
    String helloImB();
}
