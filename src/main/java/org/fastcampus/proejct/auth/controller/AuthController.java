package org.fastcampus.proejct.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class AuthController {
    private final UserInfoService service;

    @GetMapping("/form")
    public String loginForm() {
        log.info("get login");
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        log.info("get signup");
        return "register";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email
    ) {
        log.info("post signup");
        service.saveUser(username, password, email);
        return "redirect:/";
    }
}
