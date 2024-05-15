package org.demo.studentscore.model.converter;

import lombok.RequiredArgsConstructor;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.SClassMapper;
import org.demo.studentscore.model.entity.Teacher;
import org.demo.studentscore.model.vo.TeacherVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherVOConverter {
    private final CourseMapper courseMapper;
    private final SClassMapper sClassMapper;

    public TeacherVO convertToVO(Teacher teacher) {
        return new TeacherVO(
                teacher.getTno(),
                teacher.getName(),
                teacher.getSex(),
                teacher.getPhone().replaceAll("(?<=.{3}).*(?=.{3}$)", "***")
        );
    }

    public List<TeacherVO> convertToVOList(List<Teacher> Teachers) {
        List<TeacherVO> TeacherVOS = new ArrayList<>();
        for (Teacher Teacher : Teachers) {
            TeacherVOS.add(convertToVO(Teacher));
        }
        return TeacherVOS;
    }
}
