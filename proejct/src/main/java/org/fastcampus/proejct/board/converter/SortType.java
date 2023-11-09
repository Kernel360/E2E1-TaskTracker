package org.fastcampus.proejct.board.converter;

import lombok.Getter;

@Getter
public enum SortType {
    SORT_DEFAULT("default"), // 내가 참여중인 boards (작성자,멤버 참여, isFinished = false)
    SORT_SELF("self"), // 내가 작성한 것만 조회
    SORT_ALL("all"), // default + (isFinished 상관 x)
    SORT_FINISHED("finished"); // default + isFinished = true

    private final String sorted;

    SortType(String type) {
        this.sorted = type;
    }
}
