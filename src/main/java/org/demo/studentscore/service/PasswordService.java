package org.demo.studentscore.service;

public interface PasswordService {
    <T> void changePassword(Class<T> clazz, String userId, String newPassword);
}
