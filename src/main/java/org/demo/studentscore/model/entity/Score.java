package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_score")
public class Score {
    private Long sno; // 学号
    private Long cid; // 课程号
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer score;// 课程成绩
    private Boolean isFailed; // 表示是否挂科
}
