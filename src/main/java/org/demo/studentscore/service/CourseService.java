package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    void setImg(String courseId, MultipartFile file) throws DataNotFoundException;
}
