package org.fastcampus.proejct.follow.db.repository;

import org.fastcampus.proejct.follow.db.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(Long follower);

    void deleteFollowByFollowerAndFollowing(Long follower, Long following);
}
