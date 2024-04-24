
package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.RolesMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Roles;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.security.SysUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 此service实现查询数据库中需要做登陆的用户 并返回给SpringSecurity处理
 */
@Service
@RequiredArgsConstructor
public class SysUserService {
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final RolesMapper rolesMapper;


    public SysUser getUserByUsername(String username) {
        SysUser sysUser = null;
        SysUser studentSysUser = getStudentSysUser(username);
        if (studentSysUser != null) {
            sysUser = studentSysUser;
        } else {
            SysUser teacherSysUser = getTeacherSysUser(username);
            if (teacherSysUser != null) {
                sysUser = teacherSysUser;
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
            List<String> rolesByUsername = getRolesByUsername(username);
            sysUser = new SysUser(student.getSno().toString(), student.getPassword(), rolesByUsername);
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
            List<String> rolesByUsername = getRolesByUsername(username);
            sysUser = new SysUser(teacher.getTno().toString(), teacher.getPassword(), rolesByUsername);
        }
        return sysUser;
    }
}
