package org.demo.studentscore.service.impl;

import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper, StudentMapper studentMapper) {
        this.scoreMapper = scoreMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<ScoreVO> getAllById(Integer sno) {
        Student student = studentMapper.selectById(sno);
        if (student != null)
            // 调用xml中的sql语句执行多表查询
            return scoreMapper.selectAllInfo(sno);
        else {
            return null;
        }
    }
}
