package com.wzh.diary.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author wzh
 * @date 2023年09月13日 16:09
 * Description:
 */
@Configuration
public class MyCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //1 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //a 允许的域 前端地址
        config.addAllowedOrigin("http://localhost:8888");
        //b 是否发送Cookie信息
        config.setAllowCredentials(true);
        //c 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //d 允许的头信息
        config.addAllowedHeader("*");
        //2 添加映射路径 拦截哪些请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",config);
        //3 返回创建的CorsFilter
        return new CorsFilter(configurationSource);
    }

}
