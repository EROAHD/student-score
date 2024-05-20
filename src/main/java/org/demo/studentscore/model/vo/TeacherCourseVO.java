package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
// 返回教师中的课程对象结果
public class TeacherCourseVO {
    private Long cid; // 课程id
    private String name; // 课程名称
    private Long mid; // 专业编号
    private String majorName; // 专业名称
    private Integer typeId; // 课程类型
}
