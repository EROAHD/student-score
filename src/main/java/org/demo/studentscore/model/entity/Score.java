package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_score")
public class Score {
    @TableId
    private Integer cid; // 课程号
    private Long sno; // 学号
    private Double score;// 课程成绩
}
