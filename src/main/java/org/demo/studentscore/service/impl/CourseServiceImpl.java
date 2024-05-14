package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @Override
    public List<Course> getCourse(String sno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException {
        // 先通过学号查找到学生
        Student student = studentMapper.selectById(sno);
        if (student == null) {
            throw new DataNotFoundException(this.getClass() + ":学生未找到");
        }
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 然后通过专业查找课程
        courseLambdaQueryWrapper.eq(Course::getMid, student.getMid());
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courses = courseMapper.selectList(courseLambdaQueryWrapper);
        if (courses == null || courses.isEmpty()) {
            throw new DataNotFoundException(this.getClass() + ":课程未找到");
        }
        return courses;
    }
}
