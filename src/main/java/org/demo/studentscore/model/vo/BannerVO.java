package org.demo.studentscore.model.vo;

import lombok.Data;

@Data
public class BannerVO {
    private Integer bannerType; // banner的类型
    private String bannerUrl; // 设置点击图片后的跳转路径
    private String filePath; // banner作为网络图片时需要返回
}
