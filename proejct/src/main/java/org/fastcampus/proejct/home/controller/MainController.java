package org.fastcampus.proejct.home.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return "redirect:/board";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

}
