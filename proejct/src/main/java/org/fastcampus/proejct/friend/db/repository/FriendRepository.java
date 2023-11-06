package org.fastcampus.proejct.friend.db.repository;


import org.fastcampus.proejct.friend.db.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

//    @Query("DELETE  FROM Friend f WHERE f.id = :userId AND f.follow = :follow")
//    List<Friend> deleteByFollowId(@Param("userId") Long id,@Param("follow") Long follow);

}
