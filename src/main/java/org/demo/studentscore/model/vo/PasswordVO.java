package org.demo.studentscore.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordVO {
    private String oldPassword;
    private String newPassword;
}
