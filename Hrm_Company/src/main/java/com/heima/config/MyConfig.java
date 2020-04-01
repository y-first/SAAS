package com.heima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyConfig {

    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*"); //允许任何头信息
        corsConfiguration.addAllowedMethod("*"); //代表所有的请求方法
        corsConfiguration.setAllowCredentials(true); //允许带cookie
        corsConfiguration.addAllowedOrigin("http://localhost:8080"); //允许跨域的域名
        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return  new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
