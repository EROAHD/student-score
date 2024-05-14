package org.demo.studentscore.service.impl;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherInfo(String tno) throws DataNotFoundException {
        Teacher teacher = teacherMapper.selectById(tno);
        if (teacher == null) {
            throw new DataNotFoundException("教师未找到");
        }
        return teacher;
    }
}
