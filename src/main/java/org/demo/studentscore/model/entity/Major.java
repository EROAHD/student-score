package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("major")
public class Major {
    private Integer mid;
    private String name;
    private Integer majorsId;
}
