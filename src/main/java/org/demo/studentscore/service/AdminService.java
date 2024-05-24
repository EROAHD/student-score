package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Major;
import org.demo.studentscore.model.entity.SClass;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Map<String, Object> getInfo(String adminId) throws DataNotFoundException;

    List<Course> getAllCourses(Map<String, String> keywords);

    List<Major> getAllMajors(Map<String, String> keywords);

    List<SClass> getAllSClass(Map<String, String> keywords);
}
