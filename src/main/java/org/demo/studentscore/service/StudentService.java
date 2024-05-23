package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.model.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student getStudentInfo(String sno) throws DataNotFoundException;
    
    List<Map<String, Object>> getScores(String sno, String courseType) throws IncompleteRequestParameterException, DataNotFoundException;

    List<Student> getAllStudents(Map<String, String> keywords);
}
