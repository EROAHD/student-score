package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentScoreVO {
    private Long sno; // 学号
    private String name; // 姓名
    private Long courseId; // 课程号
    private String courseName; // 课程名
    private String teacher; // 老师
    private Integer score; // 成绩
    private Long mid; // 专业号
    private String majorName; // 专业名称
    private Long classId; // 班级号
    private String className; // 班级名称
}
