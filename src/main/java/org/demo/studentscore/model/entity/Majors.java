package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Majors {
    @TableId
    private Long majorsId;
    private String name;
    private String description;
    @TableLogic
    private Integer deleted;
}
