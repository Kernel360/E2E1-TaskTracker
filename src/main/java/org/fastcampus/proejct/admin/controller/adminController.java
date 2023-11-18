package org.fastcampus.proejct.admin.controller;

import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(adminController.class);

    @GetMapping ("/users")
    public String getUsers(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){
        model.addAttribute("resultList",userInfoService.getUserAll());
        return "/admin_users";
    }

}
