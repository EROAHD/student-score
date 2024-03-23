package org.demo.studentscore.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.demo.studentscore.model.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;

    @Test
    void test1() {
        Student student = new Student();
        student.setSno(1L);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sno", student.getSno());
        System.out.println(studentMapper.selectList(queryWrapper));
    }

}