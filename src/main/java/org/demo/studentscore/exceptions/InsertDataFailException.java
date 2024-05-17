package org.demo.studentscore.exceptions;

/**
 * 自定义异常 表示从数据库中未找到数据
 */
public class InsertDataFailException extends Exception {
    public InsertDataFailException(String message) {
        super(message);
    }
}
