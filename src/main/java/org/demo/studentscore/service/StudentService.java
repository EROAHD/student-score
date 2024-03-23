package org.demo.studentscore.service;

import org.demo.studentscore.model.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    
    List<Student> getPageWithProperty(Integer pageSize, Integer pageNum, Map<String, String> keywords);

    Integer addStudent(Student student);

    Integer modifyStudent(Student student);

    Integer removeStudent(Map<String, String> keywords);
}
