package org.fastcampus.proejct.user.db.repository;

import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Transactional
    @Modifying
    @Query("update UserInfo u set u.isBan = ?1 where u.id = ?2")
    int updateIsBanById(Boolean isBan, Long id);
    Optional<UserInfo> findUserInfoByEmail(String email);


}
