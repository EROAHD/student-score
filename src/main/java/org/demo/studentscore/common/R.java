package org.demo.studentscore.common;

import lombok.Data;

/**
 * 统一向前端返回结果的结果类
 */
@Data
public class R<T> {
    // 返回结果的状态枚举
    private Integer code;
    // 返回结果的提示消息
    private String msg;
    // 返回结果的类型
    private T data;

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param data 传入返回的结果
     * @param <T>  返回结果的类型
     * @return 返回R类型的对象
     */
    public static <T> R<T> success(T data) {
        return new R<>(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getName(), data);
    }


    /**
     * @param statusEnum 状态码
     * @return 返回R类型的对象
     */
    public static R<?> fail(StatusEnum statusEnum) {
        return new R<>(statusEnum.getCode(), statusEnum.getName(), null);
    }
}
