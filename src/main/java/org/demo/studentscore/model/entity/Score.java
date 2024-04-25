package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("s_score")
public class Score {
    @TableId
    private Long sno; // 学号
    @TableField
    private Long cid; // 课程号
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer score;// 课程成绩
    private Boolean isFailed; // 表示是否挂科
}
