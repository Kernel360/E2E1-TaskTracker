package org.fastcampus.proejct.notification.db.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.proejct.home.db.model.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Jiyong Jung
 * @className : Notification
 * @fileName : Notification.java
 * @date : 2023-10-30 :: 오후 4:32
 * @desc : 알람 엔티티
 * ===========================================================
 * line                  AUTHOR             NOTE
 * -----------------------------------------------------------
 * 0              Jiyong Jung        최초 생성
 */
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String type;
    @Setter
    private long senderId;
    @Setter
    private long receiverId;
    @Setter
    private String text;
    @Setter
    private boolean visible;

    @Builder
    public Notification(
            Long id,
            String type,
            long senderId,
            long receiverId,
            String text,
            boolean isVisible
    ) {
        this.id = id;
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.visible = isVisible;
    }
}
