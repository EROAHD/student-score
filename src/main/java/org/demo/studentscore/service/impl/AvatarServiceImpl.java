package org.demo.studentscore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.mapper.AvatarMapper;
import org.demo.studentscore.model.entity.Avatar;
import org.demo.studentscore.service.AvatarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AvatarServiceImpl implements AvatarService {
    private final AvatarMapper avatarMapper;

    @Override
    public List<Avatar> getHistoryAvatars(String userId) throws DataNotFoundException {
        LambdaQueryWrapper<Avatar> avatarLambdaQueryWrapper = new LambdaQueryWrapper<>();
        avatarLambdaQueryWrapper.eq(Avatar::getUserId, userId);
        List<Avatar> avatars = avatarMapper.selectList(avatarLambdaQueryWrapper);
        if (avatars == null || avatars.isEmpty()) {
            throw new DataNotFoundException("未找到用户" + userId + "的任何头像");
        }
        return avatars;
    }

    @Override
    public Avatar getLatestAvatar(String userId) throws DataNotFoundException {
        LambdaQueryWrapper<Avatar> avatarLambdaQueryWrapper = new LambdaQueryWrapper<>();
        avatarLambdaQueryWrapper.eq(Avatar::getUserId, userId);
        avatarLambdaQueryWrapper.orderByDesc(Avatar::getUploadDate);
        List<Avatar> avatars = avatarMapper.selectList(avatarLambdaQueryWrapper);
        if (avatars == null || avatars.isEmpty()) {
            throw new DataNotFoundException("未找到用户" + userId + "的任何头像");
        }
        return avatars.getFirst();
    }
}
