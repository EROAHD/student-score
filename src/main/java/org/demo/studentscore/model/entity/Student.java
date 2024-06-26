package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId
    private Long sno; // 学号
    private String name; // 姓名
    private Boolean sex;
    private String password; // 密码
    private String email; // 邮箱
    private Long mid; // 专业编号
    private Long cid; // 班级编号
}
