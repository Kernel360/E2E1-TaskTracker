package org.fastcampus.proejct.base.converter;

import lombok.Getter;

public enum RedirectType {
    REDIRECT_USER("redirect:/board?sorted=SORT_DEFAULT"),
    REDIRECT_ADMIN("redirect:/admin"),
    REDIRECT_ERROR("redirect:/error");

    @Getter
    private final String url;

    RedirectType(String url) {
        this.url = url;
    }
}
