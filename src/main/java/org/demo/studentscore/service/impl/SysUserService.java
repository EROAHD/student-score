
package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.AdminMapper;
import org.demo.studentscore.mapper.RolesMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Admin;
import org.demo.studentscore.model.entity.Roles;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.security.SysUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 此service实现查询数据库中需要做登陆的用户 并返回给SpringSecurity处理<br>
 * 其中 getXxxSysUser 方法表示 使用注入的mapper对象获取到对应对象的数据，将获取到的实体类封装为SysUser对象 <br>示例：
 * <pre>
 * {@code
 *
 * private SysUser getXxxxSysUser(String username) {
 *         SysUser sysUser = null;
 *         Xxxx xxxx = xxxxMapper.selectById(username);
 *         if (xxxx != null) {
 *             List<String> roles = getRolesByUsername(username); // 必须调用getRolesByUsername(username)方法获取对应用户的权限
 *             sysUser = new SysUser(xxxx.getField1().toString(), xxxx.getField2(), roles);
 *         }
 *         return sysUser;
 * }
 *
 * }
 * </pre>
 * </p>
 */
@Service
@RequiredArgsConstructor
public class SysUserService {
    // 获取用户权限的Mapper
    private final RolesMapper rolesMapper;
    // 实体类操作Mapper
    private final AdminMapper adminMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;


    /**
     * 通过用户名 获取数据库中所有的 匹配的 需要登录的 用户 作为 loadUserByUsername 中获取到的数据库中已存在的用户
     */
    public SysUser getUserByUsername(String username) {
        // 通过用户名在学生表中查询学生用户
        SysUser sysUser = getStudentSysUser(username);
        // 如果没有在学生表中查询到学生用户 则在教师表中查询
        if (sysUser == null) {
            sysUser = getTeacherSysUser(username);
            if (sysUser == null) {
                sysUser = getAdminSysUser(username);
            }
        }
        return sysUser;
    }

    /**
     * 通过用户名获取权限信息
     */
    private List<String> getRolesByUsername(String username) {
        List<String> rolesList = new ArrayList<>();
        List<Roles> roles = rolesMapper.selectList(new LambdaUpdateWrapper<Roles>().eq(Roles::getUsername, username));
        for (Roles role : roles) {
            rolesList.add(role.getRole());
        }
        return rolesList;
    }


    /**
     * 通过用户名查询学生信息
     */
    private SysUser getStudentSysUser(String username) {
        SysUser sysUser = null;
        Student student = studentMapper.selectById(username);
        if (student != null) {
            List<String> roles = getRolesByUsername(username);
            sysUser = new SysUser(student.getSno().toString(), student.getPassword(), roles);
        }
        return sysUser;
    }

    /**
     * 通过用户名查询老师信息
     */
    private SysUser getTeacherSysUser(String username) {
        SysUser sysUser = null;
        Teacher teacher = teacherMapper.selectById(username);
        if (teacher != null) {
            List<String> roles = getRolesByUsername(username);
            sysUser = new SysUser(teacher.getTno().toString(), teacher.getPassword(), roles);
        }
        return sysUser;
    }

    /**
     * 通过用户名查询管理员信息
     */
    private SysUser getAdminSysUser(String username) {
        SysUser sysUser = null;
        Admin admin = adminMapper.selectById(username);
        if (admin != null) {
            List<String> roles = getRolesByUsername(username);
            sysUser = new SysUser(admin.getAdminId().toString(), admin.getPassword(), roles);
        }
        return sysUser;
    }
}
