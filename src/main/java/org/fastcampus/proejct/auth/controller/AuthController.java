package org.fastcampus.proejct.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final UserInfoService service;
//
//    @GetMapping("/admin")
//    public String getAdmin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        return "index";
//    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }
}
