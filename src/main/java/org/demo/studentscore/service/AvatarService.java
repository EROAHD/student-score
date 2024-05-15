package org.demo.studentscore.service;

import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Avatar;

import java.util.List;

public interface AvatarService {
    List<Avatar> getHistoryAvatars(String userId) throws DataNotFoundException;

    Avatar getLatestAvatar(String userId) throws DataNotFoundException;
}
