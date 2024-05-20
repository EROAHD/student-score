package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.FileUploadException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.model.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BannerService {
    List<Banner> getBanners(Integer type) throws DataNotFoundException;

    String uploadBannerImg(MultipartFile file) throws DataNotFoundException, FileUploadException;

    void saveBanner(Banner banner) throws IncompleteRequestParameterException;

    void deleteBanner(String bannerId) throws IncompleteRequestParameterException;
}
