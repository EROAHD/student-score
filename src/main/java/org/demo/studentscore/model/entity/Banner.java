package org.demo.studentscore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    private String bannerId;
    private Integer bannerType;
    private String bannerUrl;
    private String filePath;
}
