package org.fastcampus.proejct.friend.converter;

import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.friend.db.model.Friend;


/**
 * DTO for {@link org.fastcampus.proejct.friend.db.model.Friend}
 */
public record FriendDto(
        Long id,
        UserInfoDto follow
) {
    public static FriendDto of(
            Long id,
            UserInfoDto follow
    ) {
        return new FriendDto(
                id,
                follow
        );
    }

    public static FriendDto from(Friend entity) {
        return FriendDto.of(
                entity.getId(),
                UserInfoDto.from(entity.getFollow())
        );
    }

}