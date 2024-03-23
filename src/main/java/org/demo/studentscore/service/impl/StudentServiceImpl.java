package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> getPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return studentMapper.selectList(null);
    }

    @Override
    public List<Student> getWithProperty(Map<String, String> keywords) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeLeft(Student::getSno, keywords.get("sno")).or();
        return studentMapper.selectList(wrapper);
    }
}
