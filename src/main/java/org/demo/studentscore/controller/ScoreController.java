package org.demo.studentscore.controller;

import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.PageInfoConverter;
import org.demo.studentscore.model.converter.ScoreVOConverter;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩相关
 */
@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
@Slf4j
public class ScoreController {
    private final ScoreService scoreService;
    private final ScoreVOConverter scoreVOConverter;
    private final PageInfoConverter pageInfoConverter;

    /**
     * 通过SpringSecurity中获取的用户名信息，以分页的形式返回学生的成绩
     *
     * @param pageSize       每页显示大小
     * @param pageNum        页号
     * @param keywords       传入关键字来按照要求筛选成绩
     * @param authentication SpringSecurity 验证对象 通过此对象获取用户名
     * @return 返回分页后的学生成绩信息
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public R<?> getScore(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords, Authentication authentication) {
        String sno = authentication.getName();
        List<Score> scores;
        try {
            scores = scoreService.getScores(sno, pageSize, pageNum, keywords);
        } catch (DataNotFoundException e) {
            log.warn(this.getClass() + ":学号为" + sno + "的学生未找到任何成绩");
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        List<ScoreVO> scoreVOS = scoreVOConverter.convertToVOList(scores);
        PageInfo<Score> scorePageInfo = new PageInfo<>(scores);
        System.out.println(scorePageInfo);
        PageInfo<ScoreVO> scoreVOPageInfo = new PageInfo<>(scoreVOS);
        System.out.println(scoreVOPageInfo);
        pageInfoConverter.pageInfoToVO(scorePageInfo, scoreVOPageInfo);
        return R.success(scoreVOPageInfo);
    }
}
