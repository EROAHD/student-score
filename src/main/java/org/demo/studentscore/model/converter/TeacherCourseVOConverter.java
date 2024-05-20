package org.demo.studentscore.model.converter;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.MajorMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Major;
import org.demo.studentscore.model.vo.TeacherCourseVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherCourseVOConverter {
    private final MajorMapper majorMapper;


    public TeacherCourseVO convertToVO(Course course) {
        Major major = majorMapper.selectOne(new LambdaUpdateWrapper<Major>().eq(Major::getMid, course.getMid()));
        return new TeacherCourseVO(
                course.getCid(),
                course.getName(),
                course.getMid(),
                major == null ? null : major.getName(), // 选修课程没有专业号，则设置为null
                course.getTypeId()
        );
    }

    /**
     * 将对象数组转换为VO对象
     */
    public List<TeacherCourseVO> convertToVOList(List<Course> courses) {
        List<TeacherCourseVO> teacherCourseVOS = new ArrayList<>();
        for (Course course : courses) {
            teacherCourseVOS.add(convertToVO(course));
        }
        return teacherCourseVOS;
    }
}
