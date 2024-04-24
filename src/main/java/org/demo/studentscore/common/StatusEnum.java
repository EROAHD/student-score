package org.demo.studentscore.common;

/**
 * 状态码枚举
 */
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
    INVALID_CREDENTIALS(3001, "用户名或密码错误");


    private final Integer code;
    private final String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
