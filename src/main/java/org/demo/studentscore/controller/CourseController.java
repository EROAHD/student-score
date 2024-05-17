package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.service.CourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("img/{courseId}")
    public R<?> setImg(@RequestParam("file") MultipartFile file, @PathVariable("courseId") String courseId) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return R.fail(StatusEnum.INVALID_FILE);
        }
        if (courseId == null)
            return R.fail(StatusEnum.FILE_UPLOAD_FAIL);
        // 获取用户名
        try {
            courseService.setImg(courseId, file);
        } catch (DataNotFoundException e) {
            log.error("文件对象" + file + "保存失败");
            return R.fail(StatusEnum.FAIL);
        }
        log.error("文件对象" + file + "保存失败");
        return R.success(null);
    }
}
