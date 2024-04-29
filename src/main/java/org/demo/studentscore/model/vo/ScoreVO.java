package org.demo.studentscore.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 删除学号字段 添加名称字段作为VO对象返回
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ScoreVO {
    @TableId
    private Long cid; // 课程号
    private String name; // 课程名称
    private Integer score; // 学生成绩 使用Integer 数据库不存储小数点成绩 成绩按照0.5自动进1
    private String teacher_name; // 老师名称
    private Boolean isFailed; // 表示是否挂科
}
