package org.fastcampus.proejct.board.repository;

import org.fastcampus.proejct.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
