package org.fastcampus.proejct.global.security;

import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.global.config.SecurityConfig;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserInfoService service;

    @BeforeTestMethod
    public void securitySetUp() {
        given(service.getUserInfo(anyString()))
                .willReturn(Optional.of(createUserInfoDto()));
        given(service.saveUser(anyString(), anyString(), anyString()))
                .willReturn(createUserInfoDto());
    }


    private UserInfoDto createUserInfoDto() {
        return UserInfoDto.of(
                1L,
                "tester",
                "password",
                "tester"
        );
    }

}