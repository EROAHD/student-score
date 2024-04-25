package org.demo.studentscore.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DBAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        log.error(this.getClass() + ":" + authException.getClass() + ":" + authException.getLocalizedMessage());
        R<?> fail = R.fail(StatusEnum.UNAUTHORIZED_ACCESS);
        if (authException instanceof BadCredentialsException) {
            fail = R.fail(StatusEnum.INVALID_CREDENTIALS);
        }
        response.getWriter().println(JSON.toJSONString(fail));
    }
}
