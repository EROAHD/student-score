package org.demo.studentscore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.service.CourseService;
import org.demo.studentscore.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseServiceImpl implements CourseService {
    private final FileService fileService;
    private final CourseMapper courseMapper;

    @Override
    public void setImg(String courseId, MultipartFile file) throws DataNotFoundException {
        String fileName = "course_" + courseId + ".jpg";
        fileService.saveFileByName(file, fileName);
    }
}
