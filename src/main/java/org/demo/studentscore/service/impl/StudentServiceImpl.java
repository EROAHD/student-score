package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Student> getStudent(Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        studentLambdaQueryWrapper.likeRight(keywords.get("sno") != null, Student::getSno, keywords.get("sno"));
        studentLambdaQueryWrapper.likeRight(keywords.get("sno") != null, Student::getSno, keywords.get("sno"));
        //
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        if (students == null || students.isEmpty()) {
            throw new DataNotFoundException("通过关键字未找到学生");
        }
        return students;
    }
}
