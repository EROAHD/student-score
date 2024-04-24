package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Teacher {
    @TableId
    private Long tno;
    private String password;
    private String name;
    private String phone;
    @TableLogic
    private Integer deleted;
}
