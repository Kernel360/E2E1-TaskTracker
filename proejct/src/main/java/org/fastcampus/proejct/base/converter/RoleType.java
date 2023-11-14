package org.fastcampus.proejct.global.converter;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String type;

    RoleType(String type) {
        this.type = type;
    }
}
