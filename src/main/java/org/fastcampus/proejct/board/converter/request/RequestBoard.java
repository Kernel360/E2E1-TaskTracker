package org.fastcampus.proejct.board.converter.request;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.dto.TaskDto;

import java.util.List;

@Slf4j
public record RequestBoard(
        String title,
        String content
) {
    public static RequestBoard of(String title, String content) {
        return new RequestBoard(title, content);
    }

    public BoardDto toDto(UserInfoDto userInfo) {
        return BoardDto.of(
                title,
                content,
                false,
                userInfo
        );
    }
}
