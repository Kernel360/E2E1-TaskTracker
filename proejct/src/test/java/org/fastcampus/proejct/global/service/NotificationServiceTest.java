package org.fastcampus.proejct.global.service;

import org.fastcampus.proejct.notification.converter.NotificationDto;
import org.fastcampus.proejct.notification.db.repository.NotificationRepository;
import org.fastcampus.proejct.notification.converter.TemplateCollection;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("알람서비스 테스트")
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notiService;

    @Mock
    private NotificationRepository notiRepository;

    @Mock
    private TemplateCollection temps;

    private static Stream<Arguments> param(){
        //Long id = 1L;
        String notiType = "addBuddy";
        long id = 1L;
        long senderId = 1L;
        long receiverId = 1L;
        String content = senderId + TemplateCollection.contentTemplate.FRIEND_ADD.getMessage();

        NotificationDto dto = new NotificationDto();
        dto.setId(id);
        dto.setType(notiType);
        dto.setSenderId(senderId);
        dto.setReceiverId(receiverId);
        dto.setText(content);

        return Stream.of(
                Arguments.of(dto)
        );
    }


    void testGetAllNotice(){

    }

    @DisplayName("알림 등록 테스트")
    @ParameterizedTest
    @MethodSource("param")
    void testAddNotice(NotificationDto notiDto){

        /* 시스템 도입시 notiType과 content template 정의해서 별개 파일로 관리 되도록 작업 진행 */

        /* given -- 32line에서 설정 완료 */

        /* when */
        boolean result = notiService.addNotice(notiDto);

        /* then */
        assertTrue(result);

    }

}