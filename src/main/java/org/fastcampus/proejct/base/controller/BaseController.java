package org.fastcampus.proejct.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.RedirectType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BaseController {
    @GetMapping("/")
    public String root() {
        //return RedirectType.REDIRECT_USER.getUrl();
        return Redirect.getInstance();
    }
}
