package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.demo.studentscore.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    private final StudentService studentService;

    /**
     * 通过学号查询一个学生已经存在的成绩
     *
     * @return 返回ScoreVO对象 包含课程编号 名称 成绩 任课教师
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public R<?> getAllById(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords) {
        List<ScoreVO> allScores = scoreService.getPageWithProperty(pageSize, pageNum, keywords);
        if (allScores != null && !allScores.isEmpty()) {
            // 通过学号查询学生的基本信息并显示
            return R.success(allScores);
        } else {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
    }

    /**
     * 通过学号确定学生并添加指定课程号的成绩
     *
     * @param score 课程对象 包含学生学号 课程号 成绩
     * @return 返回添加成绩的结果
     */
    @PostMapping
    public R<?> addScore(@RequestBody Score score) {
        Integer rows = scoreService.addScore(score);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == -1) {
            // 返回 -1 如果在score表中同时查询到学号和课程则返回数据已存在
            return R.fail(StatusEnum.INVALID_INPUT);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }

    @PutMapping
    public R<?> modifyScore(@RequestBody Score score) {
        Integer rows = scoreService.modifyScore(score);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == -1) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        } else if (rows == -2) {
            return R.fail(StatusEnum.INVALID_INPUT);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }

    @DeleteMapping
    public R<?> deleteScore(@RequestBody Score score) {
        Integer rows = scoreService.deleteScore(score);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == -1) {
            return R.fail(StatusEnum.INVALID_INPUT);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }
}
