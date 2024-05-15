package org.demo.studentscore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.common.UserFileEnum;
import org.demo.studentscore.exceptions.DataNotFoundException;
import org.demo.studentscore.model.entity.Avatar;
import org.demo.studentscore.service.AvatarService;
import org.demo.studentscore.service.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/avatar")
public class AvatarController {
    private final FileService fileService;
    private final AvatarService avatarService;

    /**
     * 接受用户头像
     */
    @PostMapping
    public R<?> receive(@RequestParam("file") MultipartFile file, Authentication authentication) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return R.fail(StatusEnum.INVALID_FILE);
        }
        // 获取用户名
        String userId = authentication.getName();
        try {
            fileService.saveFile(file, UserFileEnum.AVATAR, Long.valueOf(userId));
        } catch (DataNotFoundException e) {
            log.error("文件对象" + file + "保存失败");
            return R.fail(StatusEnum.FAIL);
        }
        return R.success(null);
    }

    /**
     * 返回用户的最新头像
     */
    @GetMapping
    public R<?> sendLatest(Authentication authentication) {
        String userId = authentication.getName();
        Avatar avatar = null;
        try {
            avatar = avatarService.getLatestAvatar(userId);
        } catch (DataNotFoundException e) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
        return R.success(avatar);
    }
}
