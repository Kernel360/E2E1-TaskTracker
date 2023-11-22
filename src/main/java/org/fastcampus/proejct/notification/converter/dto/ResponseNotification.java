package org.fastcampus.proejct.notification.converter.dto;

import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNotification {
    private String type;
    private String senderId;
    private String message;
}
