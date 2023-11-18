package org.fastcampus.proejct.auth.converter.request;

import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.dto.TaskDto;

import java.util.List;

public record RequestBoardDto(
        String title,
        String content,
        List<TaskDto> tasks,
        List<UserInfoDto> members
) {
    public static RequestBoardDto of(
            String title,
            String content,
            List<TaskDto> tasks,
            List<UserInfoDto> members
    ) {
        return new RequestBoardDto(
                title,
                content,
                tasks,
                members
        );
    }

    public BoardDto toDto(UserInfoDto host) {
        return BoardDto.of(
                title,
                content,
                host,
                tasks,
                members
        );
    }
}
