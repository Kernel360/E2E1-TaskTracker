package org.fastcampus.proejct.friend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.response.ResponseBoardDto;
import org.fastcampus.proejct.friend.converter.FriendDto;
import org.fastcampus.proejct.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/friend/{user}")
    public String getFriendsView(@PathVariable Long user) {
        ModelMap model = new ModelMap();
        List<FriendDto> friends = friendService.getFriends(user);
        model.addAttribute("friends", friends);

//        List<FriendDto> friends = friendService.getFriends(user)
//                .stream()
//                .map(FriendDto::from)
//                .toList();
//
        return "friends";
    }

    @GetMapping("/friend/{id}/add")
    public String friendAddView() {
        return "friend/add";
    }

    @PostMapping("/friend/{user}/add/{follower}")
    public String postfriendAdd(
            @PathVariable Long user,
            @PathVariable Long follower
    ) {
        friendService.addFriend(user, follower);
        return "redirect:/friend";
    }

//    @DeleteMapping("/friend/{id}/delete")
//    public String friendDelete(@PathVariable Long id, FriendDto friend) {
//        friendService.deleteFriend(id, friend);
//        return "redirect:/friend";
//    }


}
