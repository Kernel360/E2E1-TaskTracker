package org.fastcampus.proejct.global.fcm;

public record FCMNotificationRequestDto(
        Long id,
        String title,
        String body
) {
    public static FCMNotificationRequestDto of(
            Long id,
            String title,
            String body
    ) {
        return new FCMNotificationRequestDto(id, title, body);
    }
}
