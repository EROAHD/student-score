package org.demo.studentscore.controller;

import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.vo.ScoreVO;
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

    /**
     * 通过学号查询一个学生已经存在的成绩
     *
     * @param sno
     * @return
     */
    @GetMapping("/{sno}")
    public R<?> getAllById(@PathVariable("sno") Long sno) {
        List<ScoreVO> allScores = scoreService.getAllById(sno);
        if (allScores != null) {
            return R.success(allScores);
        } else {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
    }
}
