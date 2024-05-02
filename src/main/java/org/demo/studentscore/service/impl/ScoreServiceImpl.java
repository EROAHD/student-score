package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final ScoreMapper scoreMapper;

    @Override
    public List<Score> getScores(String sno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException {
        PageHelper.startPage(pageNum, pageSize);
        //
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果传入的学号不为空则用学号从成绩表中查询所有的成绩
        scoreLambdaQueryWrapper.eq(Score::getSno, sno);
        //
        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
        if (scores == null || scores.isEmpty()) {
            throw new DataNotFoundException("未找到成绩");
        }
        scores.forEach(score -> {
            if (score.getScore() < 60)
                score.setIsFailed(true);
        });
        return scores;
    }
}
