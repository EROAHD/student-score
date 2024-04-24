package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.mapper.TeacherMapper;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.ScoreVO;
import org.demo.studentscore.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;


    /**
     * 分页查询成绩的方法
     *
     * @param pageSize 每页的数量
     * @param pageNum  页号
     * @param keywords 查询关键字
     * @return ScoreVO对象
     */
    @Override
    public List<ScoreVO> getPageWithProperty(Integer pageSize, Integer pageNum, Map<String, String> keywords) {
        List<ScoreVO> scoreVOS = new ArrayList<>();
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 通过keywords中获取查询关键字进行查询
        scoreLambdaQueryWrapper.eq(keywords.get("sno") != null, Score::getSno, keywords.get("sno"));
        scoreLambdaQueryWrapper.eq(keywords.get("cid") != null, Score::getCid, keywords.get("cid"));
        scoreLambdaQueryWrapper.eq(keywords.get("isFailed") != null, Score::getIsFailed, keywords.get("isFailed"));
        // 进行分页操作
        PageHelper.startPage(pageNum, pageSize);
        // 将查询到的结果转为ScoreVO对象返回
        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
        scores.forEach(score -> {
            Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCid, score.getCid()));
            Teacher teacher = teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getTno, course.getTno()));
            ScoreVO scoreVO = new ScoreVO(score.getCid(), course.getName(), score.getScore(), teacher.getName(), score.getIsFailed());
            scoreVOS.add(scoreVO);
        });
        return scoreVOS;
    }

    /**
     * 添加成绩的方法
     *
     * @param score 成绩对象 包含学号 课程号 成绩
     * @return 返回影响的行数 -1 表示记录已存在
     */
    @Override
    public Integer addScore(Score score) {
        if (score.getSno() == null || score.getCid() == null || (score.getScore() > 100 || score.getScore() < 0))
            return -1;
        // 查询课程是否存在
        Course course = courseMapper.selectById(score.getCid());
        if (course != null) {
            // 课程存在则查询学生是否够存在 不存在则直接返回0 表示插入失败 没有此学生
            Student student = studentMapper.selectById(score.getSno());
            if (student != null) {
                List<Course> courses = courseMapper.selectList(new LambdaQueryWrapper<Course>().eq(Course::getMid, student.getMid()));
                if (!courses.contains(course)) return -3;
                LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
                scoreLambdaQueryWrapper.eq(Score::getSno, score.getSno());
                scoreLambdaQueryWrapper.eq(Score::getCid, score.getCid());
                Long rows = scoreMapper.selectCount(scoreLambdaQueryWrapper);
                if (rows != 0) {
                    return -1;
                }
                if (score.getScore() < 60) score.setIsFailed(true);
                return scoreMapper.insert(score);
            }
        }
        return -2;
    }

    @Override
    public Integer modifyScore(Score score) {
        if (score.getSno() == null || score.getCid() == null || (score.getScore() > 100 || score.getScore() < 0))
            return -1;
        LambdaUpdateWrapper<Score> scoreLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        scoreLambdaUpdateWrapper.set(Score::getSno, score.getSno());
        scoreLambdaUpdateWrapper.set(Score::getCid, score.getCid());
        scoreLambdaUpdateWrapper.set(Score::getScore, score.getScore());
        return scoreMapper.update(score, scoreLambdaUpdateWrapper);
    }

    @Override
    public Integer deleteScore(Score score) {
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (score.getSno() == null || score.getCid() == null) return -1;
        scoreLambdaQueryWrapper.eq(Score::getSno, score.getSno());
        scoreLambdaQueryWrapper.eq(Score::getCid, score.getCid());
        return scoreMapper.delete(scoreLambdaQueryWrapper);
    }
}
