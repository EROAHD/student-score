package org.demo.studentscore.service;

import org.demo.studentscore.model.vo.ScoreVO;

import java.util.List;

public interface ScoreService {
    List<ScoreVO> getAllById(Integer sno);
}
