package org.demo.studentscore.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SysUserDetailsService implements UserDetailsService {
    private final SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过调用SysUserService中的方法实现查询所有用户
        SysUser sysUser = sysUserService.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.isEnabled(),
                sysUser.isAccountNonExpired(),
                sysUser.isCredentialsNonExpired(),
                sysUser.isAccountNonLocked(),
                sysUser.getAuthorities()
        );
    }
}
