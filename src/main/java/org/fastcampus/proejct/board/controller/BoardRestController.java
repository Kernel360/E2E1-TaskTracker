package org.fastcampus.proejct.board.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.request.RequestBoard;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardRestController {
    private final UserInfoService userInfoService;
    private final BoardService boardService;
    private final TaskService taskService;
    private final NotificationService notificationService;

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

    @GetMapping("/{boardId}")
    public Api<BoardDto> getBoard(
            @PathVariable Long boardId
    ) {
        BoardDto board = boardService.getBoard(boardId);
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
            RequestBoard request
    ) {
        UserInfoDto user = userInfoService.getUserInfoId(userId).orElseThrow();
        BoardDto board = boardService.saveBoard(userId, request.toDto(user));
        return Api.<BoardDto>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(board)
                .build();
    }

    // FIXME: 11/16/23 안됨
    @PutMapping("/{userId}/updated/{boardId}")
    public Api putBoardUpdate(
            @PathVariable Long userId,
            @PathVariable Long boardId,
            @RequestBody RequestBoard request
    ) {
        UserInfoDto user = userInfoService.getUserInfoId(userId).orElseThrow();
        boardService.updateBoard(boardId, request.toDto(user));
        boardService.getBoardMember(boardId).forEach(member -> {
            notificationService.send(
                    user,
                    "게시글이 수정되었습니다.",
                    "board",
                    member.id()
            );
        });
        return Api.builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
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

    @GetMapping("/{boardId}/member")
    public Api<List<UserInfoDto>> getBoardMember(
            @PathVariable Long boardId
    ) {
        List<UserInfoDto> members = boardService.getBoardMember(boardId);
        return Api.<List<UserInfoDto>>builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .data(members)
                .build();
    }

    @PutMapping("/{boardId}/member/{memberId}")
    public Api putBoardMember(
            @PathVariable Long boardId,
            @PathVariable Long memberId
    ) {
        BoardDto board = boardService.getBoard(boardId);
        UserInfoDto user = userInfoService.getUserInfoId(memberId).orElseThrow();
        boardService.putBoardMember(boardId, memberId);
        boardService.getBoardMember(boardId).forEach(member -> {
            notificationService.send(
                    user,
                    board.title() + "에 초대되었습니다.",
                    "board",
                    member.id()
            );
        });
        return Api.builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .build();
    }

    @DeleteMapping("/{boardId}/member/{memberId}")
    public Api deleteBoardMember(
            @PathVariable Long boardId,
            @PathVariable Long memberId
    ) {
        boardService.deleteBoardMember(boardId, memberId);
        BoardDto b = boardService.getBoard(boardId);
        log.info("deleteBoardMember : {}", b);
        return Api.builder()
                .code(HttpStatus.OK.value())
                .message("정상 호출")
                .build();
    }


}
