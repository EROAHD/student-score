package org.demo.studentscore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.RecordAlreadyExistsException;
import org.demo.studentscore.model.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService extends IService<Student> {

    Integer addStudent(Student student) throws RecordAlreadyExistsException;

    Integer removeStudent(Student student) throws DataNotFoundException;

    Integer modifyStudent(Student student) throws DataNotFoundException;

    List<Student> getPageWithProperty(Integer pageSize, Integer pageNum, Map<String, String> keywords);
}
