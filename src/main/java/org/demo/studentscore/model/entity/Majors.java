package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Majors {
    @TableId
    private Long majorsId;
    private String name;
    private String description;
}
