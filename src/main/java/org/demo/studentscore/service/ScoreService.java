package org.demo.studentscore.service;

import org.demo.studentscore.model.entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getAllById(Integer sno);
}
