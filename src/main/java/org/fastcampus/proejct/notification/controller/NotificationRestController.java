package org.fastcampus.proejct.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.notification.converter.dto.ResponseNotification;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notification")
@RestController
public class NotificationRestController {
    private final ObjectMapper mapper;
    private final NotificationService notificationService;

    @GetMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) throws IOException {
        UserInfoDto principal = userPrincipal.toDto();
        // 서비스를 통해 생성된 SseEmitter를 반환
        return notificationService.connectNotification(principal.id());
    }

    @PostMapping("/send/{receiverId}")
    public Api<NotificationDto> postSend(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long receiverId,
            @RequestBody ResponseNotification response
    ) throws IOException {
        NotificationDto dto = notificationService.send(userPrincipal.toDto(), response.getMessage(), response.getType(), receiverId);
        return Api.<NotificationDto>builder()
                .code(200)
                .message("OK")
                .data(dto)
                .build();
    }

    @DeleteMapping("/{senderId}/delete/{receiverId}")
    public void followdeleteNotify(
            @PathVariable Long senderId,
            @PathVariable Long receiverId
    ) {
        notificationService.deleteFollowNotices(senderId, receiverId);
    }


    @DeleteMapping("/{userId}/deleteAll")
    public void deleteNotifications(
            @PathVariable Long userId
    ) {
        notificationService.deleteAllNotices(userId);
    }

    @GetMapping("/{userId}/{notificationId}")
    public NotificationDto getNotification(
            @PathVariable Long userId,
            @PathVariable Long notificationId
    ) {
        return notificationService.getNotice(userId, notificationId);
    }

    @GetMapping("/{userId}/list")
    public List<NotificationDto> getNotifications(
            @PathVariable Long userId
    ) {
        return notificationService.getAllNotice(userId);
    }
}
