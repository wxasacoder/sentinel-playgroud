package org.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wuxin
 * @date ${YEAR}/${MONTH}/${DAY} ${HOUR}:${MINUTE}:${SECOND}
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableDiscoveryClient
public class ApplicationB {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationB.class, args);
    }
}