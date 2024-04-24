package org.demo.studentscore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO {
    private String username;
    private String password;
    private List<String> roles;
}
