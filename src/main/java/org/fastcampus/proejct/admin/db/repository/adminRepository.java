package org.fastcampus.proejct.admin.db.repository;

import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepository extends JpaRepository <UserInfo,Long> {
}
