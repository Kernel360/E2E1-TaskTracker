package org.fastcampus.proejct.user.db.repository;

import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
