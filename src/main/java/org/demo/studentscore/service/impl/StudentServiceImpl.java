package org.demo.studentscore.service.impl;

import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.pojo.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Student> getAll() {
        return studentMapper.selectList(null);
    }
}
