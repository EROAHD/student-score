package org.demo.studentscore.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置SpringMvc相关的配置
 */
@SpringBootConfiguration
public class MVCConfig implements WebMvcConfigurer {
    /**
     * 设置跨域问题 使得只有匹配正则表达式的ip段能够访问此后端项目 否则即跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*localhost*", "*127.0.0.1*", "*192.168.*").allowedMethods("*");
    }
}
