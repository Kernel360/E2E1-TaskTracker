package org.fastcampus.proejct.global.repository;

import org.fastcampus.proejct.global.domain.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
