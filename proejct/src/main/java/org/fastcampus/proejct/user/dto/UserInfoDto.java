package org.fastcampus.proejct.user.dto;

import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.user.domain.UserInfo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link UserInfo}
 */
public record UserInfoDto(
        Long id,
        String name,
        Boolean isBan,
        Date exitDate,
        Boolean adminCheck,
        List<Board> createdBoards,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserInfoDto of(
            Long id,
            String name,
            Boolean isBan,
            Date exitDate,
            Boolean adminCheck,
            List<Board> createdBoards,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new UserInfoDto(
                id,
                name,
                isBan,
                exitDate,
                adminCheck,
                createdBoards,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static UserInfoDto from(
            UserInfo userInfo
    ) {
        return new UserInfoDto(
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getIsBan(),
                userInfo.getExitDate(),
                userInfo.getAdminCheck(),
                userInfo.getCreatedBoards(),
                userInfo.getCreatedAt(),
                userInfo.getCreatedBy(),
                userInfo.getModifiedAt(),
                userInfo.getModifiedBy()
        );
    }

    public static UserInfo toEntity(
            UserInfoDto dto
    ) {
        return UserInfo.of(
                dto.id(),
                dto.name(),
                dto.isBan(),
                dto.exitDate(),
                dto.adminCheck(),
                dto.createdBoards()
        );
    }
}