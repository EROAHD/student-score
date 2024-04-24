package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.RecordAlreadyExistsException;
import org.demo.studentscore.mapper.ScoreMapper;
import org.demo.studentscore.mapper.StudentMapper;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private final ScoreMapper scoreMapper;
    private final StudentMapper studentMapper;

    /**
     * 插入学生的操作
     *
     * @param student 传入学生对象
     * @return 返回影响的行数
     */
    @Override
    public Integer addStudent(Student student) throws RecordAlreadyExistsException {
        // 如果邮箱或者学号存在则判断为一条记录
        Long rows = studentMapper.selectCount(new LambdaQueryWrapper<Student>().eq(Student::getSno, student.getSno()).or().eq(Student::getEmail, student.getEmail()));
        if (rows != 0) {
            throw new RecordAlreadyExistsException("学生数据已存在！");
        }
        student.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(student.getPassword()));
        return studentMapper.insert(student);
    }

    /**
     * 删除学生
     *
     * @param student 传入参数
     * @return 返回影响的行数
     * @throws DataNotFoundException 抛出自定义异常 数据未找到异常 表示数据未找到
     */
    @Override
    public Integer removeStudent(Student student) throws DataNotFoundException {
        Long rows = studentMapper.selectCount(new LambdaQueryWrapper<Student>().eq(Student::getSno, student.getSno()));
        if (rows == 0) {
            throw new DataNotFoundException("未查询到学生数据，无法删除");
        }
        // 通过传入的学号删除所有的学生成绩
        scoreMapper.delete(new LambdaQueryWrapper<Score>().eq(Score::getSno, student.getSno()));
        // 通过传入的学号删除符合条件的学生
        return studentMapper.delete(new LambdaQueryWrapper<Student>().eq(Student::getSno, student.getSno()));
    }

    @Override
    public Integer modifyStudent(Student student) throws DataNotFoundException {
        Long rows = studentMapper.selectCount(new LambdaQueryWrapper<Student>().eq(Student::getSno, student.getSno()));
        if (rows == 0) {
            throw new DataNotFoundException("未找到学生！无法修改");
        }
        LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        studentLambdaUpdateWrapper.set(student.getPassword() != null, Student::getPassword, student.getPassword());
        studentLambdaUpdateWrapper.set(student.getName() != null, Student::getName, student.getName());
        studentLambdaUpdateWrapper.set(student.getEmail() != null, Student::getEmail, student.getEmail());
        studentLambdaUpdateWrapper.set(student.getMid() != null, Student::getMid, student.getMid());
        studentLambdaUpdateWrapper.set(student.getCid() != null, Student::getCid, student.getCid());
        return studentMapper.update(student, studentLambdaUpdateWrapper);
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
}
