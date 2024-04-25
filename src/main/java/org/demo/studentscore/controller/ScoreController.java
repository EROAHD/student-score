package org.demo.studentscore.controller;

import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.ScoreVOConverter;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/score")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ScoreController {
    private final ScoreService scoreService;
    private final ScoreVOConverter scoreVOConverter;
    
    @GetMapping("/{pageSize}/{pageNum}")
    public R<?> getScore(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords, Authentication authentication) {
        String sno = authentication.getName();
        List<Score> scores = null;
        try {
            scores = scoreService.getScores(sno, pageSize, pageNum, keywords);
        } catch (DataNotFoundException e) {
            log.warn(this.getClass() + ":学号为" + sno + "的学生未找到任何成绩");
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        List<ScoreVO> scoreVOS = scoreVOConverter.convertToVOList(scores);
        return R.success(new PageInfo<>(scoreVOS));
    }
}
