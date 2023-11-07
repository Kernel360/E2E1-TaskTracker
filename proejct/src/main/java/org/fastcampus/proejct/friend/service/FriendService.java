package org.fastcampus.proejct.friend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.friend.converter.FriendDto;
import org.fastcampus.proejct.friend.db.model.Friend;
import org.fastcampus.proejct.friend.db.repository.FriendRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional(readOnly = true)
    public List<FriendDto> getFriends(Long user) {
        log.info("friends : {}",friendRepository.findAllByUserId(6L));
        return friendRepository.findAllByUserId(user).stream()
                .map(FriendDto::from)
                .toList();
    }

    public void addFriend(Long user, Long follower) {
//        UserInfo follower = userInfoRepository.findById(dto.follower().id()).orElseThrow();
//        Friend friend = dto.toEntity(follower);
//        friend.setFollow(List.of());
        friendRepository.addFriend(user, follower);
    }

//    public void deleteFriend(Long id, FriendDto dto) {
//        Long follow = dto.follow().id();
//        friendRepository.deleteByFollowId(id, follow);
//    }

//    public void deleteFriend(Long userId, Long followerId) {
//        friendRepository.deleteFriend(userId, followerId);
//
//    }

}
