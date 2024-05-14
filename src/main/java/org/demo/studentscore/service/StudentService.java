package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student getStudentInfo(String sno) throws DataNotFoundException;

    List<Student> getStudent(Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException;
}
