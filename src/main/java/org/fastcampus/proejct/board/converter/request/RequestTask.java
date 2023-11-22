package org.fastcampus.proejct.board.converter.request;

import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.db.model.Board;

import java.time.LocalDateTime;

public record RequestTask(
        String content,
        String worker,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public static RequestTask of(
            String content,
            String worker,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        return new RequestTask(content, worker, startDate, endDate);
    }

    public TaskDto toDto(BoardDto board) {
        return TaskDto.of(
                null,
                content,
                worker,
                false,
                startDate,
                endDate,
                board.id(),
                board.userInfo(),
                null,
                null,
                null,
                null
        );
    }
}
