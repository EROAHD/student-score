package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.R;
import org.demo.studentscore.service.DashBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final DashBoardService dashBoardService;

    @GetMapping("/reports/student/score/{minScore}/{maxScore}")
    public R<?> getStudentScoreReports(@PathVariable String minScore, @PathVariable String maxScore) {
        Long stuCount = dashBoardService.getStudentScoreReports(minScore, maxScore);
        return R.success(stuCount);
    }

    @GetMapping("/reports/user/count/{userType}")
    public R<?> getUserCountReports(@PathVariable("userType") Integer userType) {
        Long userCount = dashBoardService.getUserCountReports(userType);
        return R.success(userCount);
    }

    @GetMapping("/reports/course/count/{courseType}")
    public R<?> getCourseCountReports(@PathVariable("courseType") Integer courseType) {
        Long courseCount = dashBoardService.getCourseCountReports(courseType);
        return R.success(courseCount);
    }
}
