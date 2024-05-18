package org.demo.studentscore.model.converter;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.*;
import org.demo.studentscore.model.entity.*;
import org.demo.studentscore.model.vo.StudentScoreVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentScoreVOConverter {
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final SElectiveMapper sElectiveMapper;
    private final ScoreMapper scoreMapper;
    private final MajorMapper majorMapper;
    private final SClassMapper sClassMapper;

    public StudentScoreVO convertToVO(Student student, Long courseId) {
        Course course = courseMapper.selectOne(new LambdaUpdateWrapper<Course>().eq(Course::getCid, courseId));
        Score score = scoreMapper.selectOne(new LambdaUpdateWrapper<Score>().eq(Score::getCid, courseId).eq(Score::getSno, student.getSno()));
        Major major = majorMapper.selectById(student.getMid());
        SClass sClass = sClassMapper.selectById(student.getCid());
        Teacher teacher = teacherMapper.selectOne(new LambdaUpdateWrapper<Teacher>().eq(Teacher::getTno, course.getTno()));

        String teacherName = teacher != null ? teacher.getName() : null;
        Integer scoreValue = score != null ? score.getScore() : null;
        String majorName = major != null ? major.getName() : null;
        String sClassName = sClass != null ? sClass.getName() : null;

        return new StudentScoreVO(
                student.getSno(),
                student.getName(),
                course.getCid(),
                course.getName(),
                teacherName,
                scoreValue,
                student.getMid(),
                majorName,
                student.getCid(),
                sClassName
        );
    }

    /**
     * 将对象数组转换为VO对象
     */
    public List<StudentScoreVO> convertToVOList(List<Student> students, Long courseId) {
        List<StudentScoreVO> studentScoreVOS = new ArrayList<>();
        students.forEach(student -> {
                    studentScoreVOS.add(convertToVO(student, courseId));
                }
        );
        return studentScoreVOS;
    }
}
