package com.irm.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by limi on 2017/10/15.
 */
@Configuration
public class WebConfig3 extends WebMvcConfigurerAdapter {

    @Override
    //攔截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor3())
                .addPathPatterns("/blogs/**")
                .excludePathPatterns("/blogs")
                .excludePathPatterns("/blogs/blog/**")
                .excludePathPatterns("/blogs/libraryblogs/**")
                .excludePathPatterns("/blogs/libraryblogs/search/**");



    }
}
