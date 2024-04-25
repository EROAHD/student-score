package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class StudentVO {
    private Long sno;
    private String name;
    private String email;
    private Long mid;
    private String major_name;
    private Long cid;
    private String class_name;
}
