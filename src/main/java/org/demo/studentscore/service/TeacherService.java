package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IllegalParameterException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.exceptions.InsertDataFailException;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher getTeacherInfo(String tno) throws DataNotFoundException;

    List<Course> getTeacherCourse(String tno, String courseType) throws IncompleteRequestParameterException;

    List<Student> getStudents(String tno, String courseType, Long courseId) throws IncompleteRequestParameterException, DataNotFoundException;

    void setStuScore(Long sno, Long courseId, Integer score) throws DataNotFoundException, InsertDataFailException, IllegalParameterException;
}
