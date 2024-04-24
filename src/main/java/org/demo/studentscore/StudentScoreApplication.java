package org.demo.studentscore;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class StudentScoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentScoreApplication.class, args);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        // 添加防止全表更新拦截器
        plusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return plusInterceptor;
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("slf4j");
    }
}
