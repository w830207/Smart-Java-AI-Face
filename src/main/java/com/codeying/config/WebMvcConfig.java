package com.codeying.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置
 * 配置静态资源访问路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置人脸图片访问路径
        // 访问路径: /face-imgs/xxx.jpg
        // 实际路径: 项目根目录/face-imgs/xxx.jpg
        registry.addResourceHandler("/face-imgs/**")
                .addResourceLocations("file:face-imgs/");
    }
}

