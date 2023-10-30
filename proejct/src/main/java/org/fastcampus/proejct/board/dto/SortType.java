package org.fastcampus.proejct.board.dto;

public enum SortType {
    SORT_DEFAULT("default"),
    SORT_ALL("all"),
    SORT_FINISHED("finished"),
    SORT_SELF("self");

    private final String sorted;

    SortType(String type) {
        this.sorted = type;
    }

    public String getSorted() {
        return sorted;
    }
}
