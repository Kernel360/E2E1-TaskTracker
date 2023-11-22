package org.fastcampus.proejct.admin.controller;

import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.notification.converter.dto.NotificationDto;
import org.fastcampus.proejct.notification.service.NotificationService;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    private final UserInfoService userInfoService;
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
        return "index";
    }

    @GetMapping ("/users")
    public String getUsers(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) throws IOException {
        Pageable pageInfo = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));

        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        Page<UserInfoDto> resultList = userInfoService.getUserAll(pageInfo);

        model.addAttribute("notifications", notifications);
        model.addAttribute("isAdmin",true);
        model.addAttribute("resultList",resultList);
        model.addAttribute("pageInfo",pageInfo);

        return "users";
    }

    @GetMapping("/users/search")
    public String getUsersSearch(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String keyword
    )throws Exception{

        Pageable pageInfo = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));

        List<NotificationDto> notifications = notificationService.getAllNotice(userPrincipal.id());
        notificationService.connectNotification(userPrincipal.id());

        Page<UserInfoDto> resultList = userInfoService.getUserAllSearch(pageInfo, keyword);

        model.addAttribute("notifications", notifications);
        model.addAttribute("isAdmin",true);
        model.addAttribute("resultList",resultList);
        model.addAttribute("pageInfo",pageInfo);

        return "users";
    }
}
