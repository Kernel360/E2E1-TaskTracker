package org.fastcampus.proejct.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Optional<UserInfoDto> getUserInfo(String email) {
        return userInfoRepository.findUserInfoByEmail(email).map(UserInfoDto::from);
    }

    @Transactional(readOnly = true)
    public Optional<UserInfoDto> getUserInfoId(Long id) {
        return userInfoRepository.findById(id).map(UserInfoDto::from);
    }

    public UserInfoDto saveUser(String email, String password, String name) {
        return UserInfoDto.from(userInfoRepository.save(UserInfo.of(email, password, name)));
    }

    public Page<UserInfoDto> getUserAll(Pageable page){
        Page<UserInfo> entity = userInfoRepository.findAll(page);
        return entity.map(UserInfoDto::from);
    }

    //사용자 밴
    public int updateIsBan(Map<String, Object> data){
        Boolean isBan = (Boolean) data.get("isBan");
        Long id = Long.valueOf(data.get("id").toString());
        return userInfoRepository.updateIsBanById(isBan, id);
    }

    public Page<UserInfoDto> getUserAllSearch(Pageable pageInfo, Object keyword) {
        Page<UserInfo> entity = userInfoRepository.findAllSearch(pageInfo, keyword);
        return entity.map(UserInfoDto::from);
    }

    public int updateAdminCheck(Map<String, Object> data){
        Boolean adminCheck = (Boolean) data.get("adminCheck");
        Long id = Long.valueOf(data.get("id").toString());
        return userInfoRepository.updateIsAdminById(adminCheck, id);
    }
}
