package org.demo.studentscore.model.converter;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.ScoreVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScoreVOConverter {
    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    public ScoreVO convertToVO(Score score) {
        Course course = courseMapper.selectOne(new LambdaUpdateWrapper<Course>().eq(Course::getCid, score.getCid()));
        Teacher teacher = teacherMapper.selectOne(new LambdaUpdateWrapper<Teacher>().eq(Teacher::getTno, course.getTno()));
        return new ScoreVO(score.getCid(), course.getName(), score.getScore(), teacher.getName(), score.getIsFailed());
    }

    public List<ScoreVO> convertToVOList(List<Score> scores) {
        List<ScoreVO> scoreVOS = new ArrayList<>();
        for (Score score : scores) {
            scoreVOS.add(convertToVO(score));
        }
        return scoreVOS;
    }
}
