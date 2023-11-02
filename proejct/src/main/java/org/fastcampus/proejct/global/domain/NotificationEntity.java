package org.fastcampus.proejct.global.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.proejct.home.db.model.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
/**
 * @className    : NotificationEntity
 * @fileName     : NotificationEntity.java
 * @author       : Jiyong Jung
 * @date         : 2023-10-30 :: 오후 4:32
 * @desc         : 알람 엔티티
 * ===========================================================
 * line                  AUTHOR             NOTE
 * -----------------------------------------------------------
 *   0              Jiyong Jung        최초 생성
 */
public class NotificationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String notiType;
    private long senderId;
    private long receiverId;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public NotificationEntity(String notiType, long senderId, long receiverId, String text) {
        this.notiType = notiType;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }
}
