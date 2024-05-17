package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.constants.CourseTypesConstants;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.mapper.*;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.SElective;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final SElectiveMapper sElectiveMapper;
    private final ScoreMapper scoreMapper;
    private final MajorMapper majorMapper;
    private final SClassMapper sClassMapper;

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

    @Override
    public List<Student> getStudents(String tno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws IncompleteRequestParameterException, DataNotFoundException {
        String courseId = keywords.get("courseId");
        String courseType = keywords.get("courseType");

        if (courseId == null || courseType == null) {
            throw new IncompleteRequestParameterException("请求参数不完整");
        }
        List<Course> courses = courseMapper.selectList(new LambdaUpdateWrapper<Course>()
                .eq(Course::getCid, courseId)
                .eq(Course::getTno, tno));
        if (courses == null || courses.isEmpty()) {
            throw new DataNotFoundException("教师无此课程");
        }
        List<Student> students = new ArrayList<>();
        switch (courseType) {
            case (CourseTypesConstants.MAIN): {
                Course course = courseMapper.selectOne(new LambdaUpdateWrapper<Course>().eq(Course::getCid, courseId));
                if (course == null)
                    throw new DataNotFoundException("必修课程未找到");
                PageHelper.startPage(pageNum, pageSize);
                students = studentMapper.selectList(new LambdaUpdateWrapper<Student>().eq(Student::getMid, course.getMid()));
            }
            break;
            case (CourseTypesConstants.ELECTIVE): {
                List<SElective> sElectives = sElectiveMapper.selectList(new LambdaUpdateWrapper<SElective>().eq(SElective::getCid, courseId));
                if (sElectives == null || sElectives.isEmpty()) {
                    throw new DataNotFoundException("选修课程未找到");
                }
                List<Long> snoList = new ArrayList<>();
                sElectives.forEach(sElective -> {
                    snoList.add(sElective.getSno());
                });
                if (snoList.isEmpty()) {
                    throw new DataNotFoundException("未找到选修此课程的学生");
                }
                PageHelper.startPage(pageNum, pageSize);
                students = studentMapper.selectList(new LambdaUpdateWrapper<Student>().in(Student::getSno, snoList));
            }
            break;
        }
        return students;
    }


}
