package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.TeacherVOConverter;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.TeacherVO;
import org.demo.studentscore.service.TeacherService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherVOConverter teacherVOConverter;

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
}
