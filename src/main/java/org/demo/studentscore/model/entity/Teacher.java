package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {
    @TableId
    private Long tno;
    private String name;
    private Boolean sex;
    private String password;
    private String phone;
}
