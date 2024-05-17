package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class CourseVO {
    private Long cid; // 课程id
    private String name; // 课程名称
    private String teacherName; // 教师名称
    private Integer typeId; // 课程类型
}
