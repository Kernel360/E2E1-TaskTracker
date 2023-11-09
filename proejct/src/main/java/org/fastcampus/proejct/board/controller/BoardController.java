package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final TaskService taskService;
    private final NotificationService notificationService;

    @GetMapping("/board")
    public String getBoardsView(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) throws IOException {
        List<BoardDto> boards = boardService.getBoards(userPrincipal.id());
        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        model.addAttribute("boards", boards);
        model.addAttribute("userId", userPrincipal.getUserId());
        model.addAttribute("notifications", notifications);
        return "tables";
    }

    @GetMapping("/board/write")
    public String getBoardWriteView() {
        return "board/write";
    }

    @PostMapping("/board/write/api")
    public String postBoardWrite(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            BoardDto board
    ) {
        boardService.writeBoard(userPrincipal.id(), board);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String getBoardDetail(@PathVariable Long id, Model model,@AuthenticationPrincipal UserPrincipal userPrincipal) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("userId", userPrincipal.getUserId());
        return "board/detail";
    }

    @GetMapping("/board/{id}/update")
    public String getBoardUpdate(@PathVariable Long id, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("userId", userPrincipal.getUserId());
        return "board/write";
    }

}