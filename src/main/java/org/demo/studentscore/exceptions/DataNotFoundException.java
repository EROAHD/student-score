package org.demo.studentscore.exceptions;

/**
 * 自定义异常 表示从数据库中未找到数据
 */
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super(message);
    }
}
