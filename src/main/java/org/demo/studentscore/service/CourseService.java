package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    <T> List<Course> getCourse(Class<T> clazz, String userId, Integer pageSize, Integer pageNum, Map<String, String> keywords) throws DataNotFoundException;
}
