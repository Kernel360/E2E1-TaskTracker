package org.fastcampus.proejct.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.global.converter.BaseResponse;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserInfoService service;

    @PostMapping("/signup")
    public BaseResponse<UserInfoDto> signup() {
        UserInfoDto dto = service.saveUser("email@email", "{noop}123", "조옹찬");
        return new BaseResponse<>(200, "정상 호출", dto);
    }
}
