package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.response.ResponseBoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final TaskService taskService;

    @GetMapping("/board")
    public String getBoardsView(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        List<BoardDto> boards = boardService.getBoards(userPrincipal.id());
        model.addAttribute("boards", boards);
        return "tables";
    }

    @GetMapping("/board/write")
    public String getBoardWriteView() {
        return "board/write";
    }

    @PostMapping("/board/write/api")
    public String postBoardWrite(
            BoardDto board
    ) {
        boardService.writeBoard(board);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String getBoardDetail(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/board/{id}/update")
    public String getBoardUpdate(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/write";
    }

    @PostMapping("/board/{id}/update")
    public String postBoardUpdate(@PathVariable Long id, BoardDto board) {
        boardService.updateBoard(id, board);
        return "redirect:/board";
    }

    @GetMapping("/board/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board";
    }
}