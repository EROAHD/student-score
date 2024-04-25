package org.demo.studentscore.service.impl;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    
    @Override
    public Student getStudentInfo(String sno) throws DataNotFoundException {
        Student student = studentMapper.selectById(sno);
        if (student == null) {
            throw new DataNotFoundException("学生未找到");
        }
        return student;
    }
}
