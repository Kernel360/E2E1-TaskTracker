package org.fastcampus.proejct.notification.db.repository;

import org.fastcampus.proejct.notification.db.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverIdAndVisibleFalse(Long receiverId);

    Notification findByIdAndReceiverId(Long notificationId, Long receiverId);

    @Modifying
    @Query("UPDATE Notification n SET n.visible = true WHERE n.receiverId = :receiverId")
    void visibleNotification(@Param("receiverId") Long receiverId);
}
