package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;

import java.util.Map;

public interface AdminService {
    Map<String, Object> getInfo(String adminId) throws DataNotFoundException;
}
