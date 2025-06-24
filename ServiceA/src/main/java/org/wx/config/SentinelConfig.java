package org.wx.config;


import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SentinelConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        config.setBlockExceptionHandler(new DefaultBlockExceptionHandler());
        config.setHttpMethodSpecify(true);
        config.setWebContextUnify(true); // 统一上下文
        config.setOriginParser(request -> request.getHeader("S-user"));
        // SentinelWebInterceptor 拦截所有接口（"/**"）
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }

    /**
     * 注解支持
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}