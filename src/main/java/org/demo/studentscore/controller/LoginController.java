package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 实现登录接口
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    @Value("${jwt.tokenHeaderName}")
    private String tokenHeaderName;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * @param user 从前端用POST请求的方式，通过获取请求体中的 username 和 password 进行登陆验证
     * @return 统一结果返回 <br>登录成功则 将配置文件中设置的token请求头名称作为键 将通过username生成的token字符串作为值 返回<br>登陆失败 则返回失败的提示
     */
    @PostMapping("${jwt.loginUri}")
    public R<?> login(@RequestBody SysUserVO user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate != null && authenticate.isAuthenticated()) {
            String username = authenticate.getName();
            log.info(this.getClass() + ":" + "用户[" + username + "]登陆成功,用户权限：" + authenticate.getAuthorities().toString());
            String token = jwtUtils.createToken("username", username);
            Map<String, Object> map = new HashMap<>();
            map.put(tokenHeaderName, token);
            map.put("userType", authenticate.getAuthorities().iterator().next().getAuthority());
            return R.success(map);
        } else {
            log.info(this.getClass() + ":" + "用户名为[" + user.getUsername() + "]的用户登陆失败");
            return R.fail(StatusEnum.INVALID_CREDENTIALS);
        }
    }
}
