package org.demo.studentscore.service;

import org.demo.studentscore.security.SysUser;

public interface SysUserService {
    /**
     * 传入用户名查询符合条件的用户 返回SysUser类型的对象对象
     * SysUser继承自UserDetails 便于将数据库中的实体类对象转换为符合SpringSecurity 操作的UserDetail对象
     *
     * @param username 传入用户名称
     * @return 返回UserDetails的子类对象
     */
    SysUser getUserByUsername(String username);
}
