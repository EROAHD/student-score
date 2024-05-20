package org.demo.studentscore.common;

import lombok.Getter;

@Getter
public enum UserFileEnum {
    AVATAR("avatar", new String[]{".jpeg", ".png", ".jpg"}, "头像"),
    BANNER("banner", new String[]{".jpeg", ".png", ".jpg"}, "首页走马灯图片");
    // 表示用户上传的文件类型
    private final String type;
    // 表示对应文件类型能够使用的后缀名
    private final String[] suffix;
    // 枚举的描述信息
    private final String desc;

    UserFileEnum(String type, String[] suffix, String desc) {
        this.type = type;
        this.suffix = suffix;
        this.desc = desc;
    }
}
