package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.common.constants.CourseTypesConstants;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.mapper.*;
import org.demo.studentscore.model.entity.*;
import org.demo.studentscore.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final ScoreMapper scoreMapper;
    private final TeacherMapper teacherMapper;
    private final SElectiveMapper sElectiveMapper;
    private final MajorMapper majorMapper;

    @Override
    public Student getStudentInfo(String sno) throws DataNotFoundException {
        Student student = studentMapper.selectById(sno);
        if (student == null) {
            throw new DataNotFoundException("学生未找到");
        }
        return student;
    }

    @Override
    public List<Map<String, Object>> getScores(String sno, String courseType) throws IncompleteRequestParameterException, DataNotFoundException {
        if (sno == null || courseType == null || courseType.isEmpty()) {
            throw new IncompleteRequestParameterException("请求参数错误");
        }
        Student student = studentMapper.selectById(sno);
        if (student == null) {
            throw new DataNotFoundException("学生查找失败");
        }
        List<Map<String, Object>> scores = new ArrayList<>();
        switch (courseType) {
            case (CourseTypesConstants.ALL):
            case (CourseTypesConstants.MAIN): {
                // 通过学生的专业号查询所有必修课程
                List<Course> courses = courseMapper.selectList(new LambdaUpdateWrapper<Course>().eq(Course::getMid, student.getMid()));
                // 遍历必修课程获取值
                courses.forEach(course -> {
                    //
                    Teacher teacher = teacherMapper.selectById(course.getTno());
                    Score score = scoreMapper.selectOne(new LambdaUpdateWrapper<Score>().eq(Score::getCid, course.getCid()).eq(Score::getSno, student.getSno()));
                    Major major = majorMapper.selectOne(new LambdaUpdateWrapper<Major>().eq(Major::getMid, course.getMid()));
                    //
                    Map<String, Object> oneScore = new HashMap<>();
                    oneScore.put("courseId", course.getCid());
                    oneScore.put("courseName", course.getName());
                    oneScore.put("courseType", course.getTypeId());
                    oneScore.put("courseMajor", major.getName());
                    oneScore.put("teacherName", (teacher == null) ? null : teacher.getName());
                    oneScore.put("score", (score == null) ? null : score.getScore());
                    scores.add(oneScore);
                });
                if (courseType.equals(CourseTypesConstants.MAIN))
                    break;
            }
            case (CourseTypesConstants.ELECTIVE): {
                // 通过学号查询选修表中此学生的所有选修课程
                List<SElective> sElectives = sElectiveMapper.selectList(new LambdaUpdateWrapper<SElective>().eq(SElective::getSno, sno));
                sElectives.forEach(sElective -> {
                    Course course = courseMapper.selectOne(new LambdaUpdateWrapper<Course>().eq(Course::getCid, sElective.getCid()));
                    Score score = scoreMapper.selectOne(new LambdaUpdateWrapper<Score>().eq(Score::getSno, sno).eq(Score::getCid, sElective.getCid()));
                    Teacher teacher = teacherMapper.selectById(course.getTno());
                    Map<String, Object> oneScore = new HashMap<>();
                    oneScore.put("courseId", course.getCid());
                    oneScore.put("courseName", course.getName());
                    oneScore.put("courseType", course.getTypeId());
                    oneScore.put("teacherName", (teacher == null) ? null : teacher.getName());
                    oneScore.put("score", (score == null) ? null : score.getScore());
                    scores.add(oneScore);
                });
            }
            break;
            default: {
                throw new IncompleteRequestParameterException("请求参数错误,输入了不存在的课程类型");
            }
        }
        return scores;
    }

    @Override
    public List<Student> getAllStudents(Map<String, String> keywords) {
        LambdaQueryWrapper<Student> studentLambdaUpdateWrapper = new LambdaQueryWrapper<>();
        studentLambdaUpdateWrapper.likeRight(StringUtils.hasText(keywords.get("sno")), Student::getSno, keywords.get("sno"));
        studentLambdaUpdateWrapper.likeRight(StringUtils.hasText(keywords.get("name")), Student::getName, keywords.get("name"));
        studentLambdaUpdateWrapper.likeRight(StringUtils.hasText(keywords.get("email")), Student::getEmail, keywords.get("email"));
        return studentMapper.selectList(studentLambdaUpdateWrapper);
    }
}
