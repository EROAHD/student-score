package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Student {
    @TableId
    private Long sno;
    @JsonIgnore
    @TableField("password")
    private String password;
    @TableField("name")
    private String name;
    private String email;
    private Long mid;
    private Long cid;
}
