package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.vo.SysUserVO;
import org.demo.studentscore.util.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {
    @Value("${jwt.tokenHeaderName}")
    private String tokenHeaderName;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public R<?> login(@RequestBody SysUserVO user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate != null && authenticate.isAuthenticated()) {
            String token = jwtUtils.createToken("username", user.getUsername());
            Map<String, Object> map = new HashMap<>();
            map.put(tokenHeaderName, token);
            return R.success(map);
        } else {
            return R.fail(StatusEnum.INVALID_CREDENTIALS);
        }
    }
}
