package org.fastcampus.proejct.notification.converter;

import lombok.*;
import org.fastcampus.proejct.notification.db.model.NotificationEntity;

import java.io.Serializable;

/**
 * DTO for {@link NotificationEntity}
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