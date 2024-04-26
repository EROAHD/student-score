package org.demo.studentscore.common;

import lombok.Getter;

/**
 * 用户角色信息的枚举类
 */
@Getter
public enum RolesEnum {
    ROLE_ADMIN("ADMIN"),
    ROLE_STUDENT("STUDENT"),
    ROLE_TEACHER("TEACHER");

    private final String role;

    RolesEnum(String role) {
        this.role = role;
    }
}