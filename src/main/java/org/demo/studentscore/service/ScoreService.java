package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    List<Score> getScores(String sno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException;
}
