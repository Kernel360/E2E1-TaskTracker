package org.fastcampus.proejct.board.converter.dto;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.db.model.Task;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.db.model.UserInfo;

import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
public record TaskDto(
        Long id,
        String content,
        String worker,
        boolean finished,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long boardId,
        UserInfoDto userInfo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static TaskDto of(
            Long id,
            String content,
            String worker,
            boolean finished,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long boardId,
            UserInfoDto userInfo,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new TaskDto(
                id,
                content,
                worker,
                finished,
                startDate,
                endDate,
                boardId,
                userInfo,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static TaskDto from(Task entity) {
        return TaskDto.of(
                entity.getId(),
                entity.getContent(),
                entity.getWorker(),
                entity.isFinished(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getBoard().getId(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Task toEntity(UserInfo userInfo, Board board) {
        return Task.of(
                id,
                content,
                worker,
                finished,
                startDate,
                endDate,
                board,
                userInfo
        );
    }
}