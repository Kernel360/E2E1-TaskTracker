package org.fastcampus.proejct.user.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.user.converter.UserInfoDto;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserInfoRestController {
    private final UserInfoService userInfoService;

    @Valid
    @GetMapping("/{userId}")
    public Api<UserInfoDto> getUserInfoId(
            @PathVariable Long userId
    ) {
        UserInfoDto userInfoDto = userInfoService.getUserInfoId(userId).orElseThrow(() -> new UsernameNotFoundException(""));
        return Api.<UserInfoDto>builder()
                .code(200)
                .message("OK")
                .data(userInfoDto)
                .build();
    }
}
