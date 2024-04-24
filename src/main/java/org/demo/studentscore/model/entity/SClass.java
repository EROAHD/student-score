package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_class")
public class SClass {
    @TableId
    private Long cid;
    private String name;
    private Long mid;
    @TableLogic
    private Integer deleted;
}
