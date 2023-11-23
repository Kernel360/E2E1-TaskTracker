package org.fastcampus.proejct.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class BaseController {
    @GetMapping("/")
    public String root() {
        //return RedirectType.REDIRECT_USER.getUrl();
        log.info("get root");
        return Redirect.getInstance();
    }
}
