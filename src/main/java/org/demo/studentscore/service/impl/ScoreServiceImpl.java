package org.demo.studentscore.service.impl;

import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.MajorMapper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    private final ScoreMapper scoreMapper;
    private final CourseMapper courseMapper;
    private final MajorMapper majorMapper;
    private final StudentMapper studentMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper, CourseMapper courseMapper, MajorMapper majorMapper, StudentMapper studentMapper) {
        this.courseMapper = courseMapper;
        this.scoreMapper = scoreMapper;
        this.majorMapper = majorMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<ScoreVO> getAllById(Integer sno) {
        return scoreMapper.selectAllInfo(sno);
    }
}
