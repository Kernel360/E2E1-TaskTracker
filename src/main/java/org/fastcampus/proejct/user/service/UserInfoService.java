package org.fastcampus.proejct.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public UserInfoDto saveUser(String email, String password, String name) {
        return UserInfoDto.from(userInfoRepository.save(UserInfo.of(email, password, name)));
    }

    public List<UserInfoDto> getUserAll(){
        return userInfoRepository.findAll().stream().map(UserInfoDto::from).toList();
    }

    //사용자 밴
    public int updateIsBan(Map<String, Object> data){
        Boolean isBan = (Boolean) data.get("isBan");
        Long id = Long.valueOf(data.get("id").toString());
        return userInfoRepository.updateIsBanById(isBan, id);
    }

}
