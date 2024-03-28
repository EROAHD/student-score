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
    private Integer cid;
    private String name; // 添加名称字段将课程名称返回
    private Integer score;
    private String teacher;
}
