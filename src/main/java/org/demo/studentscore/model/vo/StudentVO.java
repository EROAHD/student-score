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
    private Integer sno;
    private String name;
    private String email;
    private Integer mid;
    private Integer cid;
}
