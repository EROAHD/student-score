package org.demo.studentscore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.UserFileEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.AvatarMapper;
import org.demo.studentscore.model.entity.Avatar;
import org.demo.studentscore.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional

public class FileServiceImpl implements FileService {
    @Value("${upload.path}")
    private String uploadPath;
    private final AvatarMapper avatarMapper;

    public void saveFile(MultipartFile file, UserFileEnum userFileEnum, Long userId) throws DataNotFoundException {
        // 判断存放文件的路径是否存在
        File uploadFileSaveDir = new File(uploadPath);
        if (!uploadFileSaveDir.exists()) {
            boolean dirCreated = uploadFileSaveDir.mkdirs();
            if (!dirCreated) {
                throw new DataNotFoundException("上传目标文件夹创建失败");
            }
        }
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = null;
        if (fileName != null) {
            suffix = fileName.substring(fileName.lastIndexOf("."));
        }
        // 将文件名设置为时间戳
        String uniqueFileName = System.currentTimeMillis() + suffix;
        // 磁盘中存储的目标文件路径
        File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + uniqueFileName);
        log.info("文件上传到：" + uploadFile.getAbsolutePath());
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.warn("文件对象" + file + "保存失败");
        }
        // 获取文件前端访问的路径
        String reqFilePath = uploadPath + uniqueFileName;
        // 通过switch判断枚举对象
        switch (userFileEnum) {
            // 如果是头像的枚举对象则创建相应的实体类对象，调用相应的mapper插入数据表示文件存储在指定的路径
            case UserFileEnum.AVATAR: {
                Avatar avatar = new Avatar(userId, reqFilePath, new Date());
                avatarMapper.insert(avatar);
            }
            break;
            default:
                break;
        }
    }

    public void saveFileByName(MultipartFile file, String fileName) throws DataNotFoundException {
        // 判断存放文件的路径是否存在
        File uploadFileSaveDir = new File(uploadPath);
        if (!uploadFileSaveDir.exists()) {
            boolean dirCreated = uploadFileSaveDir.mkdirs();
            if (!dirCreated) {
                throw new DataNotFoundException("上传目标文件夹创建失败");
            }
        }
        // 磁盘中存储的目标文件路径
        File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + fileName);
        log.info("文件上传到：" + uploadFile.getAbsolutePath());
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.warn("文件对象" + file + "保存失败");
        }
    }
}
