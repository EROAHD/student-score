package org.demo.studentscore.service;

import org.demo.studentscore.model.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student getById(Integer id);

    List<Student> getPage(Integer pageSize, Integer pageNum);

    List<Student> getWithProperty(Map<String,String> keywords);

}
