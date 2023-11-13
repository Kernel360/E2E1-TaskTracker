package org.fastcampus.proejct.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.global.converter.BaseResponse;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

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
    public BaseResponse<NotificationDto> postSend(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long receiverId,
            @RequestBody String text
    ) throws IOException {
        NotificationDto dto = notificationService.send(userPrincipal.toDto(), receiverId, text);
        BaseResponse<NotificationDto> response = new BaseResponse<>(200, "정상 호출", dto);
        return response;
    }

    @DeleteMapping("/deleteAll")
    public void deleteNotifications(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        notificationService.deleteAllNotices(userPrincipal.id());
    }
}
