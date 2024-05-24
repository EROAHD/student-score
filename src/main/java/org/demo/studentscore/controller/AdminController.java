package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.StudentVOConverter;
import org.demo.studentscore.model.entity.*;
import org.demo.studentscore.model.vo.StudentVO;
import org.demo.studentscore.service.AdminService;
import org.demo.studentscore.service.AvatarService;
import org.demo.studentscore.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AvatarService avatarService;
    private final StudentService studentService;
    private final StudentVOConverter studentVOConverter;

    @GetMapping("/info")
    public R<?> getInfo(Authentication authentication) {
        String adminId = authentication.getName();
        Map<String, Object> adminInfo;
        try {
            adminInfo = adminService.getInfo(adminId);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(adminInfo);
    }

    @GetMapping("/course")
    public R<?> getAllCourses(@RequestParam Map<String, String> keywords) {
        List<Course> courses = adminService.getAllCourses(keywords);
        return R.success(courses);
    }

    @GetMapping("/major")
    public R<?> getAllMajors(@RequestParam Map<String, String> keywords) {
        List<Major> majors = adminService.getAllMajors(keywords);
        return R.success(majors);
    }

    @GetMapping("/class")
    public R<?> getAllSClass(@RequestParam Map<String, String> keywords) {
        List<SClass> majors = adminService.getAllSClass(keywords);
        return R.success(majors);
    }

    @GetMapping("/student")
    public R<?> getAllStudents(@RequestParam Map<String, String> keywords) {
        List<Student> students = studentService.getAllStudents(keywords);
        List<StudentVO> studentVOS = null;
        if (students != null && !students.isEmpty()) {
            studentVOS = studentVOConverter.convertToVOList(students);
        }
        return R.success(studentVOS);
    }

    @PostMapping("/student/add")
    public R<?> addStudent(@RequestBody Student student) {
        return null;
    }

    @GetMapping("/avatar/{userId}")
    public R<?> getAvatarByUserId(@PathVariable("userId") String userId) {
        Avatar avatar = null;
        try {
            avatar = avatarService.getLatestAvatar(userId);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(avatar);
    }
}
