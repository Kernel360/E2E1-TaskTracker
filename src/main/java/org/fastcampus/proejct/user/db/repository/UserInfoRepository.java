package org.fastcampus.proejct.user.db.repository;

import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Modifying
    @Query(value = "update UserInfo u set u.isBan = :isBan where u.id = :id")
    int updateIsBanById(@Param("isBan") boolean isBan, @Param("id") Long id);
    @Modifying
    @Query(value = "update UserInfo u set u.adminCheck = :adminCheck where u.id = :id")
    int updateIsAdminById(@Param("adminCheck") Boolean adminCheck, @Param("id") Long id);
    Optional<UserInfo> findUserInfoByEmail(String email);

    Page<UserInfo> findAll(Pageable pageInfo);

    @Query(value = "SELECT e FROM UserInfo e WHERE e.name LIKE CONCAT('%', :keyword, '%') OR e.email LIKE CONCAT('%', :keyword, '%')")
    Page<UserInfo> findAllSearch(Pageable pageInfo, @Param("keyword") Object keyword);

}
