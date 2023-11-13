package org.fastcampus.proejct.global.converter;

import lombok.Getter;

public enum RedirectType {
    REDIRECT_USER("redirect:/board?sorted=SORT_DEFAULT"),
    REDIRECT_ADMIN("redirect:/admin"),
    REDIRECT_ERROR("redirect:/error");

    @Getter
    private String url;

    RedirectType(String url) {
        this.url = url;
    }
}
