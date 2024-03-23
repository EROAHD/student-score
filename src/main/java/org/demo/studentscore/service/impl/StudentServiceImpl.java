package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {


    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(ScoreMapper scoreMapper, StudentMapper studentMapper) {
        this.scoreMapper = scoreMapper;
        this.studentMapper = studentMapper;
    }

    /**
     * 通过传入的键值对map模糊查询数据
     * 并且根据路径变量的值来分页
     *
     * @param pageSize 表示一页数据有多少个
     * @param pageNum  每页数据数量
     * @param keywords 模糊查询键值对
     * @return 返回学生数据List集合
     */
    @Override
    public List<Student> getPageWithProperty(Integer pageSize, Integer pageNum, Map<String, String> keywords) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(keywords.get("sno") != null, Student::getSno, keywords.get("sno"));
        wrapper.likeRight(keywords.get("name") != null, Student::getName, keywords.get("name"));
        wrapper.likeRight(keywords.get("email") != null, Student::getEmail, keywords.get("email"));
        wrapper.likeRight(keywords.get("mid") != null, Student::getMid, keywords.get("mid"));
        wrapper.likeRight(keywords.get("cid") != null, Student::getCid, keywords.get("cid"));
        PageHelper.startPage(pageNum, pageSize);
        return studentMapper.selectList(wrapper);
    }

    /**
     * 插入学生的操作
     *
     * @param student 传入学生对象
     * @return 返回影响的行数 返回-1则表示记录已存在
     */
    @Override
    public Integer addStudent(Student student) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果邮箱或者学号存在则判断为一条记录
        studentLambdaQueryWrapper.eq(Student::getSno, student.getSno()).or();
        studentLambdaQueryWrapper.eq(Student::getEmail, student.getEmail()).or();
        // 通过前端传回的学生对象的学号查询 如果行数不为0则返回-1
        Long rows = studentMapper.selectCount(studentLambdaQueryWrapper);
        if (rows != 0) {
            return -1;
        }
        return studentMapper.insert(student);
    }

    @Override
    public Integer modifyStudent(Student student) {
        LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        // 通过匹配传入学生对象的sno学号来确定要修改的数据
        studentLambdaUpdateWrapper.eq(Student::getSno, student.getSno());
        Long rows = studentMapper.selectCount(studentLambdaUpdateWrapper);
        // 如果查询到的行数为0则返回-1表示未查询到数据
        if (rows == 0) return -1;
        return studentMapper.update(student, studentLambdaUpdateWrapper);
    }

    @Override
    public Integer removeStudent(Map<String, String> keywords) {
        // 通过传入的学号删除所有的学生成绩
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(keywords.get("sno") != null, Score::getSno, keywords.get("sno"));
        scoreMapper.delete(scoreLambdaQueryWrapper);
        // 通过传入的学号删除符合条件的学生
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(keywords.get("sno") != null, Student::getSno, keywords.get("sno"));
        // 返回删除的学生个数
        return studentMapper.delete(studentLambdaQueryWrapper);
    }
}
