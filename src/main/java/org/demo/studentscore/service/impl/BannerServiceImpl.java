package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.FileUploadException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.mapper.BannerMapper;
import org.demo.studentscore.model.entity.Banner;
import org.demo.studentscore.service.BannerService;
import org.demo.studentscore.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Value("${upload.path}")
    private String uploadPath;
    private final BannerMapper bannerMapper;
    private final FileService fileService;


    @Override
    public List<Banner> getBanners(Integer type) throws DataNotFoundException {
        LambdaUpdateWrapper<Banner> bannerLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        if (type == 0 || type == 1) {
            bannerLambdaUpdateWrapper.eq(Banner::getBannerType, type);
        }
        if (type == 2) {
            bannerLambdaUpdateWrapper.eq(Banner::getBannerType, 0).or().eq(Banner::getBannerType, 1);
        }
        List<Banner> banners = bannerMapper.selectList(bannerLambdaUpdateWrapper);
        if (banners == null || banners.isEmpty()) {
            throw new DataNotFoundException("未找到banner数据");
        }
        return banners;
    }


    @Override
    public String uploadBannerImg(MultipartFile file) throws DataNotFoundException, FileUploadException {
        File uploadFileSaveDir = new File(uploadPath);
        if (!uploadFileSaveDir.exists()) {
            boolean dirCreated = uploadFileSaveDir.mkdirs();
            if (!dirCreated) {
                throw new DataNotFoundException("上传目标文件夹创建失败");
            }
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = null;
        if (originalFilename != null) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = "banner_" + new Random().ints(10, 0, 10).mapToObj(String::valueOf).collect(Collectors.joining()) + fileExtension;
        String filePath = uploadFileSaveDir.getAbsolutePath() + File.separator + fileName;
        // 磁盘中存储的目标文件路径
        File uploadFilePath = new File(filePath);
        log.info("文件上传到：" + uploadFilePath.getAbsolutePath());
        try {
            file.transferTo(uploadFilePath);
        } catch (IOException e) {
            log.warn("文件对象" + file + "保存失败");
            throw new FileUploadException("文件上传失败");
        }
        return uploadPath + fileName;
    }

    @Override
    public void saveBanner(Banner banner) throws IncompleteRequestParameterException {
        banner.setBannerId(null);
        if (banner.getBannerType() == 1 || banner.getBannerType() == 0) {
            if (StringUtils.hasText(banner.getBannerUrl()) && StringUtils.hasText(banner.getFilePath()))
                bannerMapper.insert(banner);
            else {
                throw new IncompleteRequestParameterException("请求参数不完整或请求参数格式不合法");
            }
        } else {
            throw new IncompleteRequestParameterException("走马灯对象类型错误");
        }
    }

    @Override
    public void deleteBanner(String bannerId) throws IncompleteRequestParameterException {
        if (!StringUtils.hasText(bannerId)) {
            throw new IncompleteRequestParameterException("请求参数不合法，无法解析bannerId");
        }
        bannerMapper.deleteById(bannerId);
    }
}
