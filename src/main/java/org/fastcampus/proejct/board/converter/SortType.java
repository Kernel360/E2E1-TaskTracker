package org.fastcampus.proejct.board.converter;

import lombok.Getter;

@Getter
public enum SortType {
    SORT_DEFAULT("default"), // 내가 참여중인 boards (작성자,멤버 참여, finished = false)
    SORT_SELF("self"), // 내가 작성한 것만 조회
    SORT_ALL("all"), // default + (finished 상관 x)
    SORT_FINISHED("finished"), // default + finished = true
    BOARD_ALL_FINISHED("board_all_finish"),
    BOARD_ALL("board_all");


    private final String sorted;

    SortType(String type) {
        this.sorted = type;
    }
}
