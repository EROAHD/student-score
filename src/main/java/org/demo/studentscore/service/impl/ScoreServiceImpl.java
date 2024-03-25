package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    private final ScoreMapper scoreMapper;
    private final CourseMapper courseMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper, CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
        this.scoreMapper = scoreMapper;
    }

    @Override
    public List<ScoreVO> getAllById(Long sno) {
        // 设置结果数组
        List<ScoreVO> scoreVOS = new ArrayList<>();
        // 设置通过sno查询score的wrapper参数
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getSno, sno);
        // 通过学号查询到符合条件的所有成绩
        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
        // 遍历成绩 通过单个成绩的编号查询到课程的名称
        scores.forEach(score -> {
            // 通过课程号查询课程
            Course course = courseMapper.selectById(score.getCid());
            // 设置返回的ScoreVO对象
            ScoreVO scoreVO = new ScoreVO(score.getCid(), course.getName(), score.getScore());
            // 将VO对象添加到结果数组中
            scoreVOS.add(scoreVO);
        });
        return scoreVOS;
    }
}
