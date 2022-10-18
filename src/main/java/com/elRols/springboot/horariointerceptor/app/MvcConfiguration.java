package com.elRols.springboot.horariointerceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    @Qualifier("schedulle")
    private HandlerInterceptor schedulle;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(schedulle).excludePathPatterns("/cerrado");
    }
}
