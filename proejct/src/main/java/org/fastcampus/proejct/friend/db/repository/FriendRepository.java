package org.fastcampus.proejct.friend.db.repository;


import org.fastcampus.proejct.friend.db.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

//    @Query("DELETE  FROM Friend f WHERE f.id = :userId AND f.follow = :follow")
//    List<Friend> deleteByFollowId(@Param("userId") Long id,@Param("follow") Long follow)
    //조회
//    @Query("SELECT f FROM Friend f WHERE f.user = :user")
//    List<Friend> findAllByUserId(@Param("user") Long user);

    List<Friend> findAllByUserId(Long user);

    //추가
    @Modifying
    @Query("INSERT INTO Friend (user, follower) VALUES (:user, :follower)")
    void addFriend(@Param("user") Long user, @Param("follower") Long follower);
    //삭제
//    @Modifying
//    @Query("DELETE FROM Friend WHERE user_id = :userId AND follower_id = :followerId")
//    void deleteFriend(@Param("userId") Long userId, @Param("followerId") Long followerId);
}
