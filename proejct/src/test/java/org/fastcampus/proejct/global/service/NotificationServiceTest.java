package org.fastcampus.proejct.global.service;

import org.fastcampus.proejct.global.domain.NotificationEntity;
import org.fastcampus.proejct.global.repository.NotificationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("알람서비스 테스트")
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notiService;

    @Mock
    private NotificationRepository notiRepository;

    void testGetAllNotice(){

        /* given, 등록 더미 데이터 */
        Long id = 1L;
        String notiType = "addBuddy";
        long senderId = 1L;
        long receiverId = 1L;
        String content = senderId + "님이 친구 추가를 요청 하셨습니다.";
        LocalDateTime createdAt = LocalDateTime.now();

    }

    @DisplayName("알림 등록 테스트")
    @Test
    void testAddNotice(){
        /* given */
        NotificationEntity notice = new NotificationEntity();

        Long id = 1L;
        String notiType = "addBuddy";
        long senderId = 1L;
        long receiverId = 1L;
        String content = senderId + "님이 친구 추가를 요청 하셨습니다.";
        LocalDateTime createdAt = LocalDateTime.now();

        notice.builder()
                .notiType(notiType)
                .senderId(senderId)
                .receiverId(receiverId)
                .text(content)
                .build();


        /* when */
        boolean result = notiService.setNotice(notice);
        System.err.println(result);
        /* then */
        assertTrue(result);


    }

}