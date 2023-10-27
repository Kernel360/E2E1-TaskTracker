package org.fastcampus.proejct.board.dto;

import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.user.domain.UserInfo;
import org.fastcampus.proejct.user.dto.UserInfoDto;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.fastcampus.proejct.board.domain.Board}
 */
public record BoardDto(
        Long id,
        String title,
        String content,
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
            UserInfoDto userInfo
    ) {
        return BoardDto.of(
                id,
                title,
                content,
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
            UserInfoDto userInfo,
            String createdBy,
            LocalDateTime createdAt,
            String modifiedBy,
            LocalDateTime modifiedAt
    ) {
        return new BoardDto(id, title, content, userInfo, createdBy, createdAt, modifiedBy, modifiedAt);
    }

    public static BoardDto from(Board entity) {
        return BoardDto.of(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userInfo=" + userInfo +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    public Board toEntity(UserInfo userInfo) {
        return Board.of(
                id,
                title,
                content,
                userInfo
        );
    }
}