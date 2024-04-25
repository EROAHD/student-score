package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Student;

public interface StudentService {

    Student getStudentInfo(String sno) throws DataNotFoundException;
}
