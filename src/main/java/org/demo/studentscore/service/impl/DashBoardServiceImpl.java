package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.mapper.*;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.service.DashBoardService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashBoardServiceImpl implements DashBoardService {
    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final AdminMapper adminMapper;
    private final CourseMapper courseMapper;

    @Override
    public Long getStudentScoreReports(String minScore, String maxScore) {
        LambdaUpdateWrapper<Score> scoreLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        scoreLambdaUpdateWrapper
                .gt(Score::getScore, minScore)
                .lt(Score::getScore, maxScore);
        return scoreMapper.selectCount(scoreLambdaUpdateWrapper);
    }

    @Override
    public Long getUserCountReports(Integer userType) {
        if (userType.equals(0))
            return adminMapper.selectCount(null);
        else if (userType.equals(1))
            return teacherMapper.selectCount(null);
        else if (userType.equals(2)) {
            return studentMapper.selectCount(null);
        }
        return null;
    }

    @Override
    public Long getCourseCountReports(Integer courseType) {
        LambdaUpdateWrapper<Course> courseLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        courseLambdaUpdateWrapper.eq(Course::getTypeId, courseType);
        return courseMapper.selectCount(courseLambdaUpdateWrapper);
    }
}
