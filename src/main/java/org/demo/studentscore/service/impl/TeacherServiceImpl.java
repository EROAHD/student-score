package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.constants.CourseTypesConstants;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    @Override
    public Teacher getTeacherInfo(String tno) throws DataNotFoundException {
        Teacher teacher = teacherMapper.selectById(tno);
        if (teacher == null) {
            throw new DataNotFoundException("教师未找到");
        }
        return teacher;
    }

    @Override
    public List<Course> getTeacherCourse(String tno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws IncompleteRequestParameterException {
        String type = keywords.get("type");
        if (type == null)
            throw new IncompleteRequestParameterException("请求参数不完整");
        List<Course> courses = null;
        LambdaUpdateWrapper<Course> courseLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        courseLambdaUpdateWrapper.eq(Course::getTno, tno);
        switch (type) {
            case CourseTypesConstants.ALL: {
                break;
            }
            case CourseTypesConstants.MAIN:
            case CourseTypesConstants.ELECTIVE: {
                courseLambdaUpdateWrapper.eq(Course::getTypeId, type);
                break;
            }
            default:
                throw new IncompleteRequestParameterException("请求参数错误");
        }
        PageHelper.startPage(pageNum, pageSize);
        courses = courseMapper.selectList(courseLambdaUpdateWrapper);
        return courses;
    }
}
