package org.demo.studentscore.common;

import lombok.Getter;

/**
 * 状态码枚举
 * 将所有可能出现的结果信息列举出来，作为返回到前端的code 状态码 和 msg 提示信息
 */
@Getter
public enum StatusEnum {
    SUCCESS(200, "success"),
    FAIL(500, "fail"),
    // 业务相关错误
    INVALID_INPUT(1001, "输入无效"),
    RECORD_NOT_FOUND(1002, "记录未找到"),
    RECORD_ALREADY_EXISTS(1003, "记录已存在"),
    UNAUTHORIZED_ACCESS(1004, "未经授权的访问"),
    DATABASE_ERROR(2001, "数据库错误"),
    NETWORK_ERROR(2002, "网络错误"),
    SERVER_ERROR(2003, "服务器错误"),
    TOKEN_IS_NULL(2004, "token为空"),
    INVALID_CREDENTIALS(3001, "用户名或密码错误"),
    OLD_PASSWORD_ERROR(3002, "旧密码错误"),
    INVALID_PASSWORD_FORMAT(3003, "密码格式无效"),
    INVALID_FILE(4001, "文件无效"),
    FILE_UPLOAD_FAIL(4002, "文件上传失败");

    private final Integer code;
    private final String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
