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

/**
 * 实现将数据库实体对象转换为VO对象，同时使用 Mybatis plus 的mapper接口将VO对象所需要的属性进行查询
 */
@Component
@RequiredArgsConstructor
public class ScoreVOConverter {
    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    /**
     * 将单个对象转换为VO对象
     */
    public ScoreVO convertToVO(Score score) {
        Course course = courseMapper.selectOne(new LambdaUpdateWrapper<Course>().eq(Course::getCid, score.getCid()));
        Teacher teacher = teacherMapper.selectOne(new LambdaUpdateWrapper<Teacher>().eq(Teacher::getTno, course.getTno()));
        return new ScoreVO(score.getCid(), course.getName(), score.getScore(), teacher.getName(), score.getIsFailed());
    }

    /**
     * 将对象数组转换为VO对象
     */
    public List<ScoreVO> convertToVOList(List<Score> scores) {
        List<ScoreVO> scoreVOS = new ArrayList<>();
        for (Score score : scores) {
            scoreVOS.add(convertToVO(score));
        }
        return scoreVOS;
    }
}
