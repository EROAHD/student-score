package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.StudentVOConverter;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.StudentVO;
import org.demo.studentscore.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentVOConverter studentVOConverter;

    @GetMapping
    public R<?> getStudentInfo(Authentication authentication) {
        String sno = authentication.getName();
        Student student = null;
        try {
            student = studentService.getStudentInfo(sno);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        StudentVO studentVO = studentVOConverter.convertToVO(student);
        return R.success(studentVO);
    }
}
