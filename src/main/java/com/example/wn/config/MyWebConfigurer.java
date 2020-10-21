package com.example.wn.config;

import com.example.wn.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    //把拦截器配置到项目中
    @Bean
    public LoginInterceptor getLoginIntercepter() {

        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
    //项目图片资源文件夹
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/workspace/img/");
    }

}


