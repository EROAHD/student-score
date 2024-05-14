package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Avatar;

import java.util.List;

public interface AvatarService {
    List<Avatar> getAvatars(String userId) throws DataNotFoundException;
}
