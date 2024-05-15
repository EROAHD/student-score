package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.mapper.AdminMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Admin;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.service.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordServiceImpl implements PasswordService {
    private final PasswordEncoder passwordEncoder;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final AdminMapper adminMapper;

    public <T> void changePassword(Class<T> clazz, String userId, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        if (Student.class.isAssignableFrom(clazz)) {
            LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            studentLambdaUpdateWrapper.eq(Student::getSno, userId).set(Student::getPassword, encodedPassword);
            studentMapper.update(studentLambdaUpdateWrapper);
        } else if (Teacher.class.isAssignableFrom(clazz)) {
            LambdaUpdateWrapper<Teacher> teacherLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            teacherLambdaUpdateWrapper.eq(Teacher::getTno, userId).set(Teacher::getPassword, encodedPassword);
            teacherMapper.update(teacherLambdaUpdateWrapper);
        } else if (Admin.class.isAssignableFrom(clazz)) {
            LambdaUpdateWrapper<Admin> adminLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            adminLambdaUpdateWrapper.eq(Admin::getAdminId, userId).set(Admin::getPassword, encodedPassword);
            adminMapper.update(adminLambdaUpdateWrapper);
        }
    }
}
