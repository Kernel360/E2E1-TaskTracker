package org.fastcampus.proejct.follow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.follow.converter.FollowDto;
import org.fastcampus.proejct.follow.service.FollowService;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FollowController {
    private final FollowService followService;

    @GetMapping("follow/list")
    public String getFollowView(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        UserInfoDto principal = userPrincipal.toDto();
        List<FollowDto> followList = followService.getFollow(principal.id());
        model.addAttribute("follows", followList);
        return "top";
    }

}
