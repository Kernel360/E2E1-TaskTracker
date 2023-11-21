
package org.fastcampus.proejct.follow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.follow.converter.FollowDto;
import org.fastcampus.proejct.follow.db.model.Follow;
import org.fastcampus.proejct.follow.db.repository.FollowRepository;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    public List<FollowDto> getFollow(Long follower) {
        return followRepository.findAllByFollower(follower).stream()
                .map(FollowDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserInfoDto> getFollowingUsers(Long follower) {
        return followRepository.findAllByFollower(follower).stream()
                .map(FollowDto::from)
                .map(it -> userInfoRepository.getReferenceById(it.following()))
                .map(UserInfoDto::from)
                .toList();
    }

    public void saveFollow(Long follower, Long following) {
        Follow follow = Follow.of(follower, following);
        Follow follow2 = Follow.of(following, follower);
        followRepository.save(follow);
        followRepository.save(follow2);
    }

    public void deleteFollow(Long follower, Long following) {
        followRepository.deleteFollowByFollowerAndFollowing(follower, following);
        followRepository.deleteFollowByFollowerAndFollowing(following, follower);
    }

}
