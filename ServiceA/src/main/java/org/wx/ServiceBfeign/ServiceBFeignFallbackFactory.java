package org.wx.ServiceBfeign;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author wuxin
 * @date 2025/06/24 14:28:48
 */
@Component
public class ServiceBFeignFallbackFactory implements FallbackFactory<ServiceBFeign> {

    @Override
    public ServiceBFeign create(Throwable cause) {
        if(!(cause instanceof DegradeException)){
            try {
                throw cause;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return new ServiceBFeign() {
            @Override
            public String helloImB() {
                return "我在feign接口处被降级了";
            }
        };
    }
}
