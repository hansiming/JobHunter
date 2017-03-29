package com.cszjo.jobhunter.config;

import com.cszjo.jobhunter.interceptor.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Han on 2017/3/29.
 * MVC视图控制器
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
