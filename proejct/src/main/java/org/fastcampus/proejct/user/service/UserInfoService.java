package org.fastcampus.proejct.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserInfoService {
    private final UserInfoRepository repository;

    @Transactional(readOnly = true)
    public Optional<UserInfoDto> getUserInfo(String email) {
        return repository.findUserInfoByEmail(email).map(UserInfoDto::from);
    }

    public UserInfoDto saveUser(String email, String password, String name) {
        return UserInfoDto.from(repository.save(UserInfo.of(email, password, name)));
    }
}
