package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId
    private Long cid;
    private String name;
    private Long mid;
    private Long tno;
}
