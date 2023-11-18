package org.fastcampus.proejct.board.converter.dto;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.db.model.UserInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Board}
 */
public record BoardDto(
        Long id,
        String title,
        String content,
        boolean finished,
        UserInfoDto userInfo,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt
) {
    public static BoardDto of(
            Long id,
            String title,
            String content,
            boolean finished,
            UserInfoDto userInfo
    ) {
        return BoardDto.of(
                id,
                title,
                content,
                finished,
                userInfo,
                null,
                null,
                null,
                null
        );
    }

    public static BoardDto of(
            String title,
            String content,
            boolean finished,
            UserInfoDto userInfo
    ) {
        return BoardDto.of(
                null,
                title,
                content,
                finished,
                userInfo,
                null,
                null,
                null,
                null
        );
    }

    public static BoardDto of(
            Long id,
            String title,
            String content,
            boolean finished,
            UserInfoDto userInfo,
            String createdBy,
            LocalDateTime createdAt,
            String modifiedBy,
            LocalDateTime modifiedAt
    ) {
        return new BoardDto(
                id,
                title,
                content,
                finished,
                userInfo,
                createdBy,
                createdAt,
                modifiedBy,
                modifiedAt
        );
    }

    public static BoardDto from(Board entity) {
        return BoardDto.of(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.isFinished(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    public Board toEntity(UserInfo userInfo) {
        return Board.of(
                id,
                title,
                content,
                finished,
                userInfo
        );
    }
}