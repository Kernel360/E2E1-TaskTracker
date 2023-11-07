package org.fastcampus.proejct.friend.converter;

import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.friend.db.model.Friend;


/**
 * DTO for {@link org.fastcampus.proejct.friend.db.model.Friend}
 */
public record FriendDto(
        Long id,
        UserInfoDto user,
        UserInfoDto follower
) {
    public static FriendDto of(
            Long id,
            UserInfoDto user,
            UserInfoDto follower
    ) {
        return new FriendDto(
                id,
                user,
                follower
        );
    }

    public static FriendDto from(Friend entity) {
        return FriendDto.of(
                entity.getId(),
                UserInfoDto.from(entity.getUser()),
                UserInfoDto.from(entity.getFollower())
        );
    }

    @Override
    public String toString() {
        return "FriendDto{" +
                "id=" + id +
                ", user=" + user +
                ", follower=" + follower +
                '}';
    }
}