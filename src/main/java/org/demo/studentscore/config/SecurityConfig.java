package org.demo.studentscore.config;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.RolesEnum;
import org.demo.studentscore.filter.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 配置Spring Security 相关设置
 */
@SpringBootConfiguration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.loginUri}")
    private String loginUri;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     System.out.println("SecurityFilterChain Bean加载");
    //     http.authorizeHttpRequests(authorize -> authorize
    //             .requestMatchers("/login").permitAll()
    //             .anyRequest().permitAll()
    //     );
    //     http.formLogin(
    //             configurer -> configurer.loginPage("/login")
    //     );
    //     // http.authorizeHttpRequests(
    //     //         authorize -> authorize.anyRequest().permitAll()).csrf(AbstractHttpConfigurer::disable);
    //     return http.build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(loginUri).permitAll()
                        .requestMatchers(HttpMethod.GET, "/score").hasAnyAuthority(RolesEnum.ROLE_STUDENT.getRole())
                        .requestMatchers(HttpMethod.GET, "/score").hasAnyAuthority(RolesEnum.ROLE_STUDENT.getRole())
                        .anyRequest().authenticated()
                );
        http.sessionManagement(configurer ->
                configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.logout(logout ->
                logout.logoutSuccessHandler(logoutSuccessHandler)
        );
        http.exceptionHandling(configurer ->
                configurer.authenticationEntryPoint(authenticationEntryPoint)
        );
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
