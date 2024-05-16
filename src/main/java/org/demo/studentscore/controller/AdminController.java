package org.demo.studentscore.controller;

import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.PageInfoConverter;
import org.demo.studentscore.model.converter.StudentVOConverter;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.StudentVO;
import org.demo.studentscore.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final StudentService studentService;
    private final StudentVOConverter studentVOConverter;
    private final PageInfoConverter pageInfoConverter;

    @GetMapping("/student/{pageSize}/{pageNum}")
    public R<?> getStudent(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords) {
        List<Student> students;
        try {
            students = studentService.getStudent(pageSize, pageNum, keywords);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        List<StudentVO> studentVOS = studentVOConverter.convertToVOList(students);
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        PageInfo<StudentVO> studentVOPageInfo = new PageInfo<>(studentVOS);
        pageInfoConverter.pageInfoToVO(studentPageInfo, studentVOPageInfo);
        return R.success(studentVOPageInfo);
    }
}
