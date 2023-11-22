package org.fastcampus.proejct.follow.converter;

import org.fastcampus.proejct.follow.db.model.Follow;

public record FollowDto(
        Long id,
        Long follower,
        Long following
) {
    public static FollowDto of(
            Long id,
            Long follower,
            Long following
    ) {
        return new FollowDto(
                id,
                follower,
                following
        );
    }

    public static FollowDto from(Follow entity) {
        return FollowDto.of(
                entity.getId(),
                entity.getFollower(),
                entity.getFollowing()
        );
    }
}
