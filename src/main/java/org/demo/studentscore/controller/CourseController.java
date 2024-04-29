package org.demo.studentscore.controller;

import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.converter.CourseVOConverter;
import org.demo.studentscore.model.converter.PageInfoConverter;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.vo.CourseVO;
import org.demo.studentscore.service.CourseService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {
    private final CourseService courseService;
    private final CourseVOConverter courseVOConverter;
    private final PageInfoConverter pageInfoConverter;

    @GetMapping("/{pageSize}/{pageNum}")
    public R<?> getCourse(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords, Authentication authentication) {
        String sno = authentication.getName();
        List<Course> courses;
        try {
            courses = courseService.getCourse(sno, pageSize, pageNum, keywords);
        } catch (DataNotFoundException e) {
            log.warn(this.getClass() + ":学号为" + sno + "的学生未找到任何课程");
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        // 将课程对象转换为VO对象
        List<CourseVO> courseVOS = courseVOConverter.convertToVOList(courses);
        // 获取原始课程对象和VO对象的PageInfo
        PageInfo<Course> coursePageInfo = new PageInfo<>(courses);
        PageInfo<CourseVO> courseVOPageInfo = new PageInfo<>(courseVOS);
        // 由于转换为VO对象后PageInfo属性失效，将原有对象的PageInfo属性交给VO对象
        pageInfoConverter.pageInfoToVO(coursePageInfo, courseVOPageInfo);
        return R.success(courseVOPageInfo);
    }
}
