package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.exceptions.InsertDataFailException;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    Teacher getTeacherInfo(String tno) throws DataNotFoundException;

    List<Course> getTeacherCourse(String tno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws IncompleteRequestParameterException;

    List<Student> getStudents(String tno, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws IncompleteRequestParameterException, DataNotFoundException;

    void setStuScore(Long sno, Long courseId, Integer score) throws DataNotFoundException, InsertDataFailException;
}
