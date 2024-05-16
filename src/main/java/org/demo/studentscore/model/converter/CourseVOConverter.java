package org.demo.studentscore.model.converter;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.CourseVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseVOConverter {
    private final TeacherMapper teacherMapper;


    public CourseVO convertToVO(Course course) {
        Teacher teacher = teacherMapper.selectOne(new LambdaUpdateWrapper<Teacher>().eq(Teacher::getTno, course.getTno()));
        return new CourseVO(course.getCid(), course.getName(), teacher.getName(), course.getTypeId());
    }

    /**
     * 将对象数组转换为VO对象
     */
    public List<CourseVO> convertToVOList(List<Course> courses) {
        List<CourseVO> courseVOS = new ArrayList<>();
        for (Course course : courses) {
            courseVOS.add(convertToVO(course));
        }
        return courseVOS;
    }
}
