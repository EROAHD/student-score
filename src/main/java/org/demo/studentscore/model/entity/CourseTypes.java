package org.demo.studentscore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypes {
    private Integer typeId;
    private String typeName;
    private String description;
}
