package org.demo.studentscore.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class StudentVO {
    @TableId
    private Long sno;
    private String name;
    private String email;
    private Long mid;
    private Long cid;
}
