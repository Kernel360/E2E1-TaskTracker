package org.fastcampus.proejct.friend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.friend.converter.FriendDto;
import org.fastcampus.proejct.friend.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FriendController {

    private FriendService friendService;

    @GetMapping("/friend/{id}")
    public String getFriendsView(@PathVariable Long id, Model model) {
        List<FriendDto> friends = friendService.getFriends(id);
        model.addAttribute("friends", friends);
        return "friends";
    }

    @GetMapping("/friend/{id}/add")
    public String friendAddView() {
        return "friend/add";
    }

    @PostMapping("/friend/{id}/add/")
    public String postfriendAdd(
            FriendDto friend
    ) {
        friendService.addFriend(friend);
        return "redirect:/friend";
    }

//    @DeleteMapping("/friend/{id}/delete")
//    public String friendDelete(@PathVariable Long id, FriendDto friend) {
//        friendService.deleteFriend(id, friend);
//        return "redirect:/friend";
//    }


}
