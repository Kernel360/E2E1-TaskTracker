package org.fastcampus.proejct.board.converter.dto;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;

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
        List<TaskDto> tasks,
        List<UserInfoDto> members,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt
) {
    public static BoardDto of(
            Long id,
            String title,
            String content,
            boolean isFinished,
            UserInfoDto userInfo,
            List<TaskDto> tasks,
            List<UserInfoDto> members
    ) {
        return BoardDto.of(
                id,
                title,
                content,
                isFinished,
                userInfo,
                tasks,
                members,
                null,
                null,
                null,
                null
        );
    }

    public static BoardDto of(
            String title,
            String content,
            boolean isFinished,
            UserInfoDto userInfo,
            List<TaskDto> tasks,
            List<UserInfoDto> members
    ) {
        return BoardDto.of(
                null,
                title,
                content,
                isFinished,
                userInfo,
                tasks,
                members,
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
            boolean isFinished,
            UserInfoDto userInfo,
            List<TaskDto> tasks,
            List<UserInfoDto> members,
            String createdBy,
            LocalDateTime createdAt,
            String modifiedBy,
            LocalDateTime modifiedAt
    ) {
        return new BoardDto(id, title, content, isFinished, userInfo, tasks, members, createdBy, createdAt, modifiedBy, modifiedAt);
    }

    public static BoardDto from(Board entity) {
        return BoardDto.of(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.isFinished(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getTasks().stream().map(TaskDto::from).toList(),
                entity.getMembers().stream().map(UserInfoDto::from).toList(),
//                entity.getMembers().stream().map(BoardMemberDto::from).toList(),
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