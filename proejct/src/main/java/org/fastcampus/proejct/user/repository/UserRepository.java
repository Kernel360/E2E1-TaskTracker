package org.fastcampus.proejct.user.repository;

import org.fastcampus.proejct.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
}