package org.demo.studentscore.filter;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.util.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    @Lazy
    private final UserDetailsService userDetailsService;

    // 通过配置文件读取登录请求uri
    @Value("${jwt.loginUri}")
    private String loginUri;

    // 包含token的请求头名称
    @Value("${jwt.tokenHeaderName}")
    private String tokenHeaderName;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String requestURI = request.getRequestURI();
        // 如果登录请求uri包含登录路径 则直接放行
        if (requestURI.startsWith(loginUri)) {
            filterChain.doFilter(request, response);
            return;
        }
        //
        String token = request.getHeader(tokenHeaderName);
        // 判断token是否为空
        if (!StringUtils.hasText(token)) {
            String result = JSON.toJSONString(R.fail(StatusEnum.TOKEN_IS_NULL));
            response.getWriter().write(result);
            return;
        }
        // 判断token是否有效
        if (jwtUtils.isExpired(token)) {
            String result = JSON.toJSONString(R.fail(StatusEnum.UNAUTHORIZED_ACCESS));
            response.getWriter().write(result);
            return;
        }
        //
        String username = jwtUtils.parseToken(token).get("username", String.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            String result = JSON.toJSONString(R.fail(StatusEnum.UNAUTHORIZED_ACCESS));
            response.getWriter().write(result);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
