package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.auth.converter.request.RequestBoardDto;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @GetMapping
    public String getBoardsView(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String sorted,
            Model model
    ){

        log.trace("trace message");
        log.debug("debug message");
        log.info("info message"); // default
        log.warn("warn message");
        log.error("error message");

        try {
            List<BoardDto> boards = boardService.getBoards(userPrincipal.id(), SortType.valueOf(sorted));
            List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
            notificationService.connectNotification(userPrincipal.id());

            model.addAttribute("boards", boards);
            model.addAttribute("userId", userPrincipal.getUserId());
            model.addAttribute("username", userPrincipal.getUsername());
            model.addAttribute("notifications", notifications);

            logger.info("getBoardsView - reading board list...");

            return "tables";
        }catch (IOException e){
            logger.error("getBoardsView - fatal error IOException at getBoardsView",e);
            return "tables";
        }
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
        /**
         * 맴버를 추가하려면 로그인 사용자의 친구 목록 필요합니다.
         * **/
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

    @GetMapping("/update/{id}")
    public String updateBoardForm(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        /**
         * 맴버를 추가하려면 로그인 사용자의 친구 목록 필요합니다.
         * **/
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("userId", userPrincipal.getUserId());
        return "board/update";
    }

    @PutMapping("/update/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            RequestBoardDto request
    ) {
        boardService.updateBoard(id, request.toDto(userPrincipal.toDto()));
        return "redirect:/board";
    }
}