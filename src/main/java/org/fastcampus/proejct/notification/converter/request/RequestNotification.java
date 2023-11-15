package org.fastcampus.proejct.notification.converter.request;

public record RequestNotification(
        Long id,
        Long senderId,
        Long receiverId,
        String text
) {

}
