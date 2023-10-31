package org.fastcampus.proejct.global.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.fastcampus.proejct.global.domain.NotificationEntity}
 */
@Data
public class NotificationDto implements Serializable {
    String notiType;
    long senderId;
    long receiverId;
    String text;
    long id;

    public NotificationDto () {
    }

}