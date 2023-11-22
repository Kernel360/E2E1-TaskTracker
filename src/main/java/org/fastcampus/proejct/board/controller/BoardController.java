package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.converter.request.RequestBoard;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.follow.converter.FollowDto;
import org.fastcampus.proejct.follow.service.FollowService;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final FollowService followService;
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @GetMapping
    public String getBoardsView(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String sorted,
            Model model
    ) {

        log.trace("trace message");
        log.debug("debug message");
        log.info("info message"); // default
        log.warn("warn message");
        log.error("error message");

        try {
            List<BoardDto> boards = boardService.getBoards(userPrincipal.id(), SortType.valueOf(sorted));
            List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
            notificationService.connectNotification(userPrincipal.id());
            List<UserInfoDto> friends = followService.getFollowingUsers(userPrincipal.id());

            model.addAttribute("boards", boards);
            model.addAttribute("userId", userPrincipal.getUserId());
            model.addAttribute("username", userPrincipal.getUsername());
            model.addAttribute("notifications", notifications);
            model.addAttribute("isAdmin", true);
            model.addAttribute("friends", friends);

            logger.info("getBoardsView - reading board list...");

            return "tables";
        } catch (IOException e) {
            logger.error("getBoardsView - fatal error IOException at getBoardsView", e);
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
        List<TaskDto> tasks = taskService.getTasks(id);
        List<UserInfoDto> members = boardService.getBoardMember(id);
        List<UserInfoDto> friends = followService.getFollowingUsers(userPrincipal.id());
        model.addAttribute("board", board);
        model.addAttribute("tasks", tasks);
        model.addAttribute("members", members);
        model.addAttribute("friends", friends);
        model.addAttribute("userId", userPrincipal.getUserId());
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
            RequestBoard request
    ) {
        Long boardId = boardService.saveBoard(request.toDto(userPrincipal.toDto()));
        return "redirect:/board/" + boardId;
    }

    @GetMapping("/write/{id}")
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

    @PostMapping("/write/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            RequestBoard request
    ) {
        boardService.updateBoard(id, request.toDto(userPrincipal.toDto()));
        return "redirect:/board/" + id;
    }

    @DeleteMapping("{id}/delete")
    public String deleteBoard(
            @PathVariable Long id
    ) {
        boardService.deleteBoard(id);
        return "redirect:/board?sorted=SORT_DEFAULT";
    }
}