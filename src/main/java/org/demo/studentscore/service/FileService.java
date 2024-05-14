package org.demo.studentscore.service;

import org.demo.studentscore.common.UserFileEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFile(MultipartFile file, UserFileEnum userFileEnum, Long userId) throws DataNotFoundException;
}
