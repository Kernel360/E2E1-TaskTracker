package org.fastcampus.proejct.notification.converter;

import lombok.*;
import org.fastcampus.proejct.notification.db.model.Notification;

import java.io.Serializable;

/**
 * DTO for {@link Notification}
 */
@Data
public class NotificationDto {
    long id;
    String type;
    long senderId;
    long receiverId;
    String text;

    public NotificationDto(long id, String type, long senderId, long receiverId, String text) {
        this.id = id;
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    public NotificationDto() {

    }

    public static NotificationDto from(Notification entity) {
        return new NotificationDto(
                entity.getId(),
                entity.getType(),
                entity.getSenderId(),
                entity.getReceiverId(),
                entity.getText()
        );
    }

}