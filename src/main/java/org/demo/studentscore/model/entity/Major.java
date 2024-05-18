package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("major")
public class Major {
    @TableId
    private Long mid;
    private String name;
    private Long majorsId; // 专业大类id
}
