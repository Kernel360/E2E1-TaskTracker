package org.fastcampus.proejct.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NAME_NOT_FOUND("유저를 찾을 수 없습니다."),
    BOARD_NOT_FOUND("게시글을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR("서버 오류");
    private final String value;

}
