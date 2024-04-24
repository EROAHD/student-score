package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Student {
    @TableId
    @NotNull
    private Long sno; // 学号
    @TableField("password")
    @NotEmpty
    private String password; // 密码
    @TableField("name")
    private String name; // 姓名
    private String email; // 邮箱
    private Long mid; // 专业编号
    private Long cid; // 班级编号
    @TableLogic
    private Integer deleted; // 逻辑删除
}
