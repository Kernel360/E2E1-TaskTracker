package org.fastcampus.proejct.board.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;

import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardRestController {
    private final UserInfoService userInfoService;
    private final BoardService boardService;

    @GetMapping("/{userId}/list")
    public Api<List<BoardDto>> getBoards(
            @PathVariable Long userId,
            @RequestParam String sorted
    ) {
        // 게시글 목록을 조회합니다.
        var boards = boardService.getBoards(userId, SortType.valueOf(sorted));

        // FIXME: 11/16/23 Builder 써도 되는데 안써도 됩니다. -> Builder가 익숙하시면 쓰시면 되요
        return Api.<List<BoardDto>>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(boards)
                .build();
    }

    @GetMapping("/{userId}")
    public Api<BoardDto> getBoard(
            @PathVariable Long userId
    ) {
        BoardDto board = boardService.getBoard(userId);
        return Api.<BoardDto>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(board)
                .build();
    }

    // FIXME: 11/16/23 안됨
    @PostMapping("/{userId}/write")
    public Api<BoardDto> postBoardCreate(
            @PathVariable Long userId,
            BoardDto request
    ) {
        BoardDto board = boardService.writeBoard(userId, request);
        return Api.<BoardDto>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(board)
                .build();
    }

    // FIXME: 11/16/23 안됨
    @PutMapping("/{userId}/updated/{boardId}")
    public Api<BoardDto> putBoardUpdate(
            @PathVariable Long userId,
            @PathVariable Long boardId,
            BoardDto request
    ) {
        BoardDto board = boardService.updateBoard(boardId, request);
        return Api.<BoardDto>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(board)
                .build();
    }

    // FIXME: 11/16/23 안됨
    @PutMapping("/finished/{boardId}")
    public Api putBoardFinished(
            @PathVariable Long boardId
    ) {
        boardService.finishedBoard(boardId);
        return Api.builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .build();
    }

    @DeleteMapping("/{boardId}/delete")
    public Api deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return Api.<Boolean>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .build();
    }
}
