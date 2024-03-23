package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_score")
public class Score {
    @TableId
    private Integer sno;
    private Integer cid;
    private Double score;
}
