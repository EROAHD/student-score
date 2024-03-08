package org.demo.studentscore.pojo;

import lombok.Data;

@Data
public class Student {
    private Integer sno;
    private String password;
    private String name;
    private String email;
    private Integer mid;
    private Integer cid;
}
