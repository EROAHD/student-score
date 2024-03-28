package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Student {
    @TableId
    private Integer sno; // 学号
    @TableField("password")
    private String password; // 密码
    @TableField("name")
    private String name; // 姓名
    private String email; // 邮箱
    private Integer mid; // 专业编号
    private Integer cid; // 班级编号
    @TableLogic
    private Integer deleted; // 逻辑删除
}
