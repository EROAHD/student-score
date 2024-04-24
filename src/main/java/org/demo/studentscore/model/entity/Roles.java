package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Roles {
    @TableId
    private String username;
    private String role;
}
