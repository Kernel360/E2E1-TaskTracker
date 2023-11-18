package org.fastcampus.proejct.admin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.controller.BoardController;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    private UserInfoService userInfoService;
    private final NotificationService notificationService;

    public adminController(UserInfoService userInfoService, BoardService boardService, TaskService taskService, NotificationService notificationService) {
        this.userInfoService = userInfoService;
        this.notificationService = notificationService;
    }

    private static final Logger logger = LoggerFactory.getLogger(adminController.class);

    @GetMapping
    public String adminView(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) throws IOException {
        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        model.addAttribute("notifications", notifications);
        model.addAttribute("isAdmin",true);
        return "/index";
    }

    @GetMapping ("/users")
    public String getUsers(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) throws IOException {
        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        model.addAttribute("notifications", notifications);
        model.addAttribute("isAdmin",true);
        model.addAttribute("resultList",userInfoService.getUserAll());
        return "/users";
    }
}
