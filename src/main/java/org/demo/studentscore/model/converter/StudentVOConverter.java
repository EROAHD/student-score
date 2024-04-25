package org.demo.studentscore.model.converter;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.MajorMapper;
import org.demo.studentscore.mapper.SClassMapper;
import org.demo.studentscore.model.entity.Major;
import org.demo.studentscore.model.entity.SClass;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.StudentVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentVOConverter {
    private final MajorMapper majorMapper;
    private final CourseMapper courseMapper;
    private final SClassMapper sClassMapper;

    public StudentVO convertToVO(Student student) {
        Major major = majorMapper.selectOne(new LambdaUpdateWrapper<Major>().eq(Major::getMid, student.getMid()));
        SClass sClass = sClassMapper.selectOne(new LambdaUpdateWrapper<SClass>().eq(SClass::getCid, student.getCid()));
        return new StudentVO(student.getSno(), student.getName(), student.getEmail(), student.getMid(), major.getName(), student.getCid(), sClass.getName());
    }

    public List<StudentVO> convertToVOList(List<Student> students) {
        List<StudentVO> studentVOS = new ArrayList<>();
        for (Student student : students) {
            studentVOS.add(convertToVO(student));
        }
        return studentVOS;
    }
}
