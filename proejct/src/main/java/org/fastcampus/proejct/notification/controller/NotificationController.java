package org.fastcampus.proejct.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.global.converter.BaseResponse;
import org.fastcampus.proejct.notification.converter.NotificationDto;
import org.fastcampus.proejct.notification.converter.request.RequestNotification;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final ObjectMapper mapper;
    private final NotificationService notificationService;

    @GetMapping("/subscribe")
    public SseEmitter subscribe() throws IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        log.info("context : {}", context);
        UserInfoDto principal = mapper.convertValue(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                UserInfoDto.class
        );
        // 서비스를 통해 생성된 SseEmitter를 반환
        return notificationService.connectNotification(principal.id());
    }

    @PostMapping("/send/{receiverId}")
    public BaseResponse<NotificationDto> postSend(
            @PathVariable Long receiverId,
            @RequestBody String text
    ) throws IOException {
        NotificationDto dto = notificationService.send(receiverId, text);
        BaseResponse<NotificationDto> response = new BaseResponse<>(200, "정상 호출", dto);
        return response;
    }
}
