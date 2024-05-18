package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {
    @TableId
    private Long adminId;
    private String password;
    private String name;
    private String email;
    private String phone;
}
