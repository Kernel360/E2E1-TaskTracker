package org.fastcampus.proejct.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.notification.db.model.Notification;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.db.repository.EmitterRepository;
import org.fastcampus.proejct.notification.db.repository.NotificationRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

/**
 * @author : Jiyong Jung
 * @className : NotificationService
 * @fileName : NotificationService.java
 * @date : 2023-10-30 :: 오후 4:29
 * @desc : 알림 기능 CRUD
 * ===========================================================
 * line                  AUTHOR             NOTE
 * -----------------------------------------------------------
 * 0              Jiyong Jung        최초 생성
 * 39             Jiyong Jung        args NotificationDTO 받도록 수정
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class NotificationService {
    private final static Long DEFAULT_TIMEOUT = 6500000L;
    private final NotificationRepository notificationRepository;
    private final UserInfoRepository userInfoRepository;
    private final EmitterRepository emitterRepository;

    public SseEmitter connectNotification(Long userId) throws IOException {
        // 새로운 SseEmitter를 만든다
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

        // 유저 ID로 SseEmitter를 저장한다.
        emitterRepository.save(userId, sseEmitter);

        // 세션이 종료될 경우 저장한 SseEmitter를 삭제한다.
        sseEmitter.onCompletion(() -> emitterRepository.delete(userId));
        sseEmitter.onTimeout(() -> emitterRepository.delete(userId));

        // 503 Service Unavailable 오류가 발생하지 않도록 첫 데이터를 보낸다.
        sseEmitter.send(SseEmitter.event().id(userId.toString()).name("alarm").data("Connection completed"));
        return sseEmitter;
    }

    public NotificationDto send(
            UserInfoDto sender,
            String text,
            String type,
            Long receiverId
    ) {
        // 유저 ID로 SseEmitter를 찾아 이벤트를 발생 시킨다.
        emitterRepository.get(receiverId).ifPresentOrElse(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event().id(receiverId.toString())
                        .name("alarm")
                        .data(text));
            } catch (IOException e) {
                // IOException이 발생하면 저장된 SseEmitter를 삭제하고 예외를 발생시킨다.
                emitterRepository.delete(receiverId);
                e.printStackTrace();
            }
        }, () -> log.info("No emitter found"));
        Notification notification = new Notification().builder()
                .type(type)
                .receiverId(receiverId)
                .senderId(sender.id())
                .text(text)
                .build();

        return NotificationDto.from(notificationRepository.save(notification));
    }

    /**
     * @param NotificationDto param
     * @return boolean
     */
    public boolean addNotice(NotificationDto param) {
        boolean ret = false;
        try {
            ModelMapper mapper = new ModelMapper();
            Notification notice = mapper.map(param, Notification.class);
            notificationRepository.save(notice);
            ret = true;
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }
        return ret;
    }

    public List<NotificationDto> getAllNotice(Long id) {
        // TODO: 11/9/23 유저와 관계 매핑해야함
        return notificationRepository.findByReceiverIdAndVisibleFalse(id).stream().map(NotificationDto::from).toList();
    }

    public void getNotice() {
        //notificationRepository.save();
    }

    public boolean deleteNotice() {

        return true;
    }

    public void deleteAllNotices(Long userId) {
        notificationRepository.visibleNotification(userId);
    }
}

