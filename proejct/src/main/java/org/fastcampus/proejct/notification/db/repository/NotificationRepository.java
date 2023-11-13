package org.fastcampus.proejct.notification.db.repository;

import org.fastcampus.proejct.notification.db.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverIdAndVisibleFalse(Long id);

    @Modifying
    @Query("UPDATE Notification SET visible = true WHERE receiverId = :receiverId")
    void visibleNotification(long receiverId);
}
