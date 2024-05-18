package org.demo.studentscore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.AdminMapper;
import org.demo.studentscore.model.entity.Admin;
import org.demo.studentscore.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public Map<String, Object> getInfo(String adminId) throws DataNotFoundException {
        Admin admin = adminMapper.selectById(adminId);
        if (admin == null) {
            throw new DataNotFoundException("管理员用户信息未找到！");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("adminId", admin.getAdminId());
        res.put("name", admin.getName());
        res.put("email", admin.getEmail());
        res.put("phone", admin.getPhone());
        return res;
    }
}
