package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.auth.converter.request.RequestBoardDto;
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
@RequestMapping("/board")
@Controller
public class BoardController {
    private final BoardService boardService;
    private final TaskService taskService;
    private final NotificationService notificationService;

    @GetMapping
    public String getBoardsView(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) throws IOException {
        List<BoardDto> boards = boardService.getBoards(userPrincipal.id());
        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        model.addAttribute("boards", boards);
        model.addAttribute("userId", userPrincipal.getUserId());
        model.addAttribute("username", userPrincipal.getUsername());
        model.addAttribute("notifications", notifications);
        return "tables";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("userId", userPrincipal.getUserId());
        model.addAttribute("tasks", board.tasks());
        return "board/detail";
    }

    @GetMapping("/write")
    public String writeBoardForm() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeBoard(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            RequestBoardDto request
    ) {
        boardService.writeBoard(request.toDto(userPrincipal.toDto()));
        return "redirect:/board";
    }

    @GetMapping("/write/{id}")
    public String updateBoardForm(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("userId", userPrincipal.getUserId());
        return "board/write";
    }

    @PutMapping("/write/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            RequestBoardDto request
    ) {
        boardService.updateBoard(id, request.toDto(userPrincipal.toDto()));
        return "redirect:/board";
    }
//
//    @DeleteMapping("/{id}/delete")
//    public String deleteBoard(@PathVariable Long id) {
//        boardService.deleteBoard(id);
//        return "redirect:/board";
//    }
}