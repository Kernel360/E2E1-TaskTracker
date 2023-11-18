package org.fastcampus.proejct.auth.converter.dto;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.user.db.model.UserInfo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public record UserInfoDto(
        Long id,
        String email,
        String password,
        String name,
        Boolean isBan,
        Date exitDate,
        Boolean adminCheck,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static UserInfoDto of(
            Long id,
            String email,
            String password,
            String name
    ) {
        return UserInfoDto.of(
                id,
                email,
                password,
                name,
                false,
                null,
                false,
                null,
                null,
                null,
                null
        );
    }

    public static UserInfoDto of(
            Long id,
            String email,
            String password,
            String name,
            Boolean isBan,
            Date exitDate,
            Boolean adminCheck,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new UserInfoDto(
                id,
                email,
                password,
                name,
                isBan,
                exitDate,
                adminCheck,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static UserInfoDto from(
            UserInfo userInfo
    ) {
        return new UserInfoDto(
                userInfo.getId(),
                userInfo.getEmail(),
                userInfo.getPassword(),
                userInfo.getName(),
                userInfo.getIsBan(),
                userInfo.getExitDate(),
                userInfo.getAdminCheck(),
                userInfo.getCreatedAt(),
                userInfo.getCreatedBy(),
                userInfo.getModifiedAt(),
                userInfo.getModifiedBy()
        );
    }

    public UserInfo toEntity() {
        return UserInfo.of(
                id,
                email,
                password,
                name,
                isBan,
                exitDate,
                adminCheck
        );
    }
}