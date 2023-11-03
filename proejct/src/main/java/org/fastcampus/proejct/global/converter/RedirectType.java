package org.fastcampus.proejct.global.converter;

import lombok.Getter;

public enum RedirectType {
    REDIRECT_USER("redirect:/admin"),
    REDIRECT_ADMIN("redirect:/board"),
    REDIRECT_ERROR("redirect:/error");

    @Getter
    private String url;

    RedirectType(String url) {
        this.url = url;
    }
}
