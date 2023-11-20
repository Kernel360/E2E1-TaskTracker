package org.fastcampus.proejct.follow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.follow.converter.FollowDto;
import org.fastcampus.proejct.follow.service.FollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowRestController {
    private final FollowService followService;

    @GetMapping("/{followerId}/list")
    public List<FollowDto> getFollows(
            @PathVariable Long followerId
    ) {
        return followService.getFollow(followerId);
    }

    @PostMapping("{followerId}/send/{followingId}")
    public void saveFollow(
            @PathVariable Long followerId,
            @PathVariable Long followingId
    ) {
        followService.saveFollow(followerId, followingId);
    }

    @DeleteMapping("{followerId}/delete/{followingId}")
    public void deleteFollow(
            @PathVariable Long followerId,
            @PathVariable Long followingId
    ) {
        followService.deleteFollow(followerId, followingId);
    }
}
