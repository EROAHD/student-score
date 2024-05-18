package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherVO {
    private Long tno;
    private String name;
    private Boolean sex;
    private String phone;
}
