package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {


    private final ScoreMapper scoreMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public List<Score> getAllById(Integer sno) {
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getSno, sno);
        return scoreMapper.selectList(scoreLambdaQueryWrapper);
    }
}
