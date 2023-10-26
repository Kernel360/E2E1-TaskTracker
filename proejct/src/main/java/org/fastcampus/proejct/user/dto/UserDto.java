package org.fastcampus.proejct.user.dto;

import org.fastcampus.proejct.user.domain.User;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO for {@link org.fastcampus.proejct.user.domain.User}
 */
public record UserDto(
        String password,
        String name,
        Date regist_date,
        Date update_date,
        Boolean accessCheck,
        Date exitDate,
        Boolean adminCheck,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserDto of(
            String password,
            String name,
            Date regist_date,
            Date update_date,
            Boolean accessCheck,
            Date exitDate,
            Boolean adminCheck
    ) {
        return UserDto.of(
                password,
                name,
                regist_date,
                update_date,
                accessCheck,
                exitDate,
                adminCheck,
                null,
                null,
                null,
                null
        );
    }

    public static UserDto of(
            String password,
            String name,
            Date regist_date,
            Date update_date,
            Boolean accessCheck,
            Date exitDate,
            Boolean adminCheck,
            String createdBy,
            LocalDateTime createdAt,
            String modifiedBy,
            LocalDateTime modifiedAt
    ) {
        return new UserDto(password, name, regist_date, update_date, accessCheck, exitDate, adminCheck, createdBy, createdAt, modifiedBy, modifiedAt);
    }

    public static UserDto from(User entity) {
        return UserDto.of(
                entity.getPassword(),
                entity.getName(),
                entity.getRegist_date(),
                entity.getUpdate_date(),
                entity.getAccessCheck(),
                entity.getExitDate(),
                entity.getAdminCheck(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    public static User toEntity(UserDto dto) {
        return User.of(
                null,
                dto.password(),
                dto.name(),
                dto.regist_date(),
                dto.update_date(),
                dto.accessCheck(),
                dto.exitDate(),
                dto.adminCheck()
        );
    }

}