package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.exceptions.FileUploadException;
import org.demo.studentscore.exceptions.IncompleteRequestParameterException;
import org.demo.studentscore.model.entity.Banner;
import org.demo.studentscore.service.BannerService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/banner")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("/{type}")
    public R<?> getBanners(@PathVariable("type") Integer type) {
        if (type == null)
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        List<Banner> banners;
        try {
            banners = bannerService.getBanners(type);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(banners);
    }

    // 此方法仅处理文件上传
    @PostMapping("/uploadImg")
    public R<?> uploadBannerImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.fail(StatusEnum.INVALID_FILE);
        }
        String filePath = null;
        try {
            filePath = bannerService.uploadBannerImg(file);
        } catch (DataNotFoundException | FileUploadException e) {
            return R.fail(StatusEnum.FILE_UPLOAD_FAIL);
        }
        return R.success(filePath);
    }

    @PostMapping("/save")
    public R<?> saveBanner(@RequestBody Banner banner) {
        if (banner == null) {
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        }
        try {
            bannerService.saveBanner(banner);
        } catch (Exception e) {
            return R.fail(StatusEnum.RECORD_INSERT_FAIL);
        }
        return R.success(null);
    }


    // 删除banner
    @DeleteMapping("/{bannerId}")
    public R<?> deleteBanner(@PathVariable("bannerId") String bannerId) {
        if (!StringUtils.hasText(bannerId))
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        try {
            bannerService.deleteBanner(bannerId);
        } catch (IncompleteRequestParameterException e) {
            log.warn("删除编号未[" + bannerId + "]的banner失败");
            return R.fail(StatusEnum.REQUEST_PARAMS_ERROR);
        }
        return R.success(null);
    }
}
