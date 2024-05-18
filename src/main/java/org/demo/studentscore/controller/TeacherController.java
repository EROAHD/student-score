package org.demo.studentscore.controller;

import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IllegalParameterException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.exceptions.InsertDataFailException;
import org.demo.studentscore.model.converter.PageInfoConverter;
import org.demo.studentscore.model.converter.StudentScoreVOConverter;
import org.demo.studentscore.model.converter.TeacherVOConverter;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.StudentScoreVO;
import org.demo.studentscore.model.vo.TeacherVO;
import org.demo.studentscore.service.TeacherService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherVOConverter teacherVOConverter;
    private final StudentScoreVOConverter studentScoreVOConverter;
    private final PageInfoConverter pageInfoConverter;

    @GetMapping("/info")
    public R<?> getTeacherInfo(Authentication authentication) {
        String tno = authentication.getName();
        Teacher teacher = null;
        try {
            teacher = teacherService.getTeacherInfo(tno);
        } catch (DataNotFoundException e) {
            log.warn("教师号为" + tno + "的教师不存在");
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        TeacherVO TeacherVO = teacherVOConverter.convertToVO(teacher);
        return R.success(TeacherVO);
    }

    @GetMapping("/course/{courseType}")
    public R<?> getTeacherCourse(Authentication authentication,
                                 @PathVariable("courseType") String courseType) {
        //
        if (courseType == null)
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        String tno = authentication.getName();
        List<Course> courses = null;
        try {
            courses = teacherService.getTeacherCourse(tno, courseType);
        } catch (IncompleteRequestParameterException e) {
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        }
        if (courses == null) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(courses);
    }

    @GetMapping("/students/{courseType}/{courseId}")
    public R<?> getStudents(Authentication authentication, @PathVariable("courseType") String courseType, @PathVariable("courseId") Long courseId) {
        String tno = authentication.getName();
        List<Student> students = null;
        try {
            students = teacherService.getStudents(tno, courseType, courseId);
        } catch (IncompleteRequestParameterException e) {
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        if (students == null || students.isEmpty()) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        List<StudentScoreVO> studentScoreVOS = studentScoreVOConverter.convertToVOList(students, courseId);
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        PageInfo<StudentScoreVO> studentScoreVOPageInfo = new PageInfo<>(studentScoreVOS);
        pageInfoConverter.pageInfoToVO(studentPageInfo, studentScoreVOPageInfo);
        return R.success(studentScoreVOPageInfo);
    }

    @PutMapping("/student/{sno}/course/{courseId}/score/{score}")
    public R<?> setStuScore(@PathVariable("sno") Long sno, @PathVariable("courseId") Long courseId, @PathVariable("score") Integer score) {
        try {
            teacherService.setStuScore(sno, courseId, score);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        } catch (InsertDataFailException e) {
            return R.fail(StatusEnum.RECORD_INSERT_FAIL);
        } catch (IllegalParameterException e) {
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        }
        return R.success(null);
    }
}
