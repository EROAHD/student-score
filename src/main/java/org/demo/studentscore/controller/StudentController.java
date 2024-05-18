package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.model.converter.PageInfoConverter;
import org.demo.studentscore.model.converter.ScoreVOConverter;
import org.demo.studentscore.model.converter.StudentVOConverter;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.StudentVO;
import org.demo.studentscore.service.ScoreService;
import org.demo.studentscore.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 学生相关操作
 */
@RequestMapping("/student")
@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final ScoreService scoreService;
    private final ScoreVOConverter scoreVOConverter;
    private final StudentService studentService;
    private final StudentVOConverter studentVOConverter;
    private final PageInfoConverter pageInfoConverter;

    /**
     * 通过SpringSecurity验证对象获取用户名 再使用用户名查询学生的信息
     */
    @GetMapping("/info")
    public R<?> getStudentInfo(Authentication authentication) {
        String sno = authentication.getName();
        Student student = null;
        try {
            student = studentService.getStudentInfo(sno);
        } catch (DataNotFoundException e) {
            log.warn("学号为" + sno + "的学生不存在");
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        StudentVO studentVO = studentVOConverter.convertToVO(student);
        return R.success(studentVO);
    }

    @GetMapping("/score/{courseType}")
    public R<?> getStudentScores(Authentication authentication, @PathVariable("courseType") String courseType) {
        String sno = authentication.getName();
        List<Map<String, Object>> scores;
        try {
            scores = studentService.getScores(sno, courseType);
        } catch (IncompleteRequestParameterException e) {
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(scores);
    }
}
