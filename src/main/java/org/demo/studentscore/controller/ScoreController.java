package org.demo.studentscore.controller;

import org.demo.studentscore.common.R;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{sno}")
    public R<?> getAllById(@PathVariable("sno") Integer sno) {
        List<Score> allById = scoreService.getAllById(sno);
        return R.success(allById);
    }
}
