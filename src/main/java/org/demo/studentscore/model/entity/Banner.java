package org.demo.studentscore.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @TableId
    private String bannerId;
    private Integer bannerType;
    private String bannerUrl;
    private String filePath;
}
