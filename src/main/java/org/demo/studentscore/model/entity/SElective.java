package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("s_elective")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SElective {
    private Long sno;
    private Long cid;
}
