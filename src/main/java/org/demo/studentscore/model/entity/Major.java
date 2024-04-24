package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("major")
public class Major {
    private Long mid;
    private String name;
    private Long majorsId;
}
