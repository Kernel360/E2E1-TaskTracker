package org.fastcampus.proejct.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return Redirect.getInstance();
    }
}
