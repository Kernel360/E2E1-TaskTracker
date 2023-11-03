package org.fastcampus.proejct.global.converter;

import lombok.Getter;

public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    @Getter
    private String type;

    RoleType(String type) {
        this.type = type;
    }
}
