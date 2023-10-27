package org.fastcampus.proejct.board.dto;

public record ResponseBoardDto(
        Long id,
        String title,
        String content,
        String host
) {
    public static ResponseBoardDto of(
            Long id,
            String title,
            String content,
            String host
    ) {
        return new ResponseBoardDto(
                id,
                title,
                content,
                host
        );
    }

    public static ResponseBoardDto from(BoardDto dto) {
        String name = dto.userInfo().name();
        if (name.isBlank() || name.isEmpty()) {
            //todo exception
        }
        return ResponseBoardDto.of(dto.id(), dto.title(), dto.content(), name);
    }
}
