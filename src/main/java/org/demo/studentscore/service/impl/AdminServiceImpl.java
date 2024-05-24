package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.AdminMapper;
import org.demo.studentscore.mapper.CourseMapper;
import org.demo.studentscore.mapper.MajorMapper;
import org.demo.studentscore.mapper.SClassMapper;
import org.demo.studentscore.model.entity.Admin;
import org.demo.studentscore.model.entity.Course;
import org.demo.studentscore.model.entity.Major;
import org.demo.studentscore.model.entity.SClass;
import org.demo.studentscore.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final CourseMapper courseMapper;
    private final MajorMapper majorMapper;
    private final SClassMapper sClassMapper;

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

    @Override
    public List<Course> getAllCourses(Map<String, String> keywords) {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return courseMapper.selectList(courseLambdaQueryWrapper);
    }

    @Override
    public List<Major> getAllMajors(Map<String, String> keywords) {
        LambdaQueryWrapper<Major> majorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return majorMapper.selectList(majorLambdaQueryWrapper);
    }

    @Override
    public List<SClass> getAllSClass(Map<String, String> keywords) {
        LambdaQueryWrapper<SClass> sClassLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return sClassMapper.selectList(sClassLambdaQueryWrapper);
    }
}
