package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.entity.Admin;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.PasswordVO;
import org.demo.studentscore.service.PasswordService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专门处理修改密码的接口
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/password")
public class PasswordController {
    private final PasswordService passwordService;
    private final AuthenticationManager authenticationManager;

    @PutMapping
    public R<?> changePassword(@RequestBody PasswordVO passwordVO, Authentication authentication) {
        if (passwordVO.getOldPassword() == null || passwordVO.getNewPassword() == null) {
            return R.fail(StatusEnum.INVALID_INPUT);
        }
        if (!passwordVO.getNewPassword().matches("^.{8,20}$")) {
            return R.fail(StatusEnum.INVALID_PASSWORD_FORMAT);
        }
        String userId = authentication.getName();
        // 调用SpringSecurity 验证旧密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, passwordVO.getOldPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断条件
        if (!authenticate.isAuthenticated()) {
            return R.fail(StatusEnum.OLD_PASSWORD_ERROR);
        }
        String role = authentication.getAuthorities().stream().toList().getFirst().getAuthority();
        switch (role.toLowerCase()) {
            case ("student"): {
                passwordService.changePassword(Student.class, userId, passwordVO.getNewPassword());
            }
            break;
            case ("teacher"): {
                passwordService.changePassword(Teacher.class, userId, passwordVO.getNewPassword());
            }
            break;
            case ("admin"): {
                passwordService.changePassword(Admin.class, userId, passwordVO.getNewPassword());
            }
            break;
        }
        return R.success(null);
    }

}
