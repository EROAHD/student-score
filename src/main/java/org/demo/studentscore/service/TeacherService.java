package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Teacher;

public interface TeacherService {
    Teacher getTeacherInfo(String tno) throws DataNotFoundException;
}
