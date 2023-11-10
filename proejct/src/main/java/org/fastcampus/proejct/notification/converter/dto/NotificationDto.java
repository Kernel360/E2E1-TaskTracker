package org.fastcampus.proejct.notification.converter.dto;

import lombok.*;
import org.fastcampus.proejct.notification.db.model.Notification;

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
    boolean visible;

    public NotificationDto(
            Long id,
            String type,
            long senderId,
            long receiverId,
            String text,
            boolean visible
    ) {
        this.id = id;
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.visible = visible;
    }

    public NotificationDto() {

    }

    public static NotificationDto from(Notification entity) {
        return new NotificationDto(
                entity.getId(),
                entity.getType(),
                entity.getSenderId(),
                entity.getReceiverId(),
                entity.getText(),
                entity.isVisible()
        );
    }

}