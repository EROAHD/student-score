package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> getBanners(Integer type) throws DataNotFoundException;
}
