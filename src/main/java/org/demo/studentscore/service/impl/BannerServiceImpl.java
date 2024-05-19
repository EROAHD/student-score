package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.BannerMapper;
import org.demo.studentscore.model.entity.Banner;
import org.demo.studentscore.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;

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
}
