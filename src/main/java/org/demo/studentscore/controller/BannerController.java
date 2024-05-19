package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Banner;
import org.demo.studentscore.service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
