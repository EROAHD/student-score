package org.demo.studentscore.service;

import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;

import java.util.List;

public interface ScoreService {
    List<ScoreVO> getAllById(Long sno);
}
