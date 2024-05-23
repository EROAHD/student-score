package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypes {
    @TableId
    private Integer typeId;
    private String typeName;
    private String description;
}
