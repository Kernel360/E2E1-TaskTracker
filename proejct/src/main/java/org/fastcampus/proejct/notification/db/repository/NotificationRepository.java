package org.fastcampus.proejct.notification.db.repository;

import org.fastcampus.proejct.notification.db.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
