package org.demo.studentscore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    private Long userId;
    private String savePath;
    private Date uploadDate;
}
