package org.fastcampus.proejct.board.converter.dto;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.db.model.Task;

import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
public record TaskDto(
        Long id,
        String content,
        String worker,
        LocalDateTime startDate,
        LocalDateTime endDate,
//        BoardDto board,
//        UserInfoDto userInfo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static TaskDto of(
            Long id,
            String content,
            String worker,
            LocalDateTime startDate,
            LocalDateTime endDate,
//            BoardDto board,
//            UserInfoDto userInfo,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new TaskDto(
                id,
                content,
                worker,
                startDate,
                endDate,
//                board,
//                userInfo,
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
                entity.getStartDate(),
                entity.getEndDate(),
//                BoardDto.from(entity.getBoard()),
//                UserInfoDto.from(entity.getUserInfo()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Task toEntity() {
        return Task.of(
                id,
                content,
                worker,
                startDate,
                endDate
//                board
//                userInfo
        );
    }
}