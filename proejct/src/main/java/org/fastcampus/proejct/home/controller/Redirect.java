package org.fastcampus.proejct.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.global.converter.RedirectType;
import org.fastcampus.proejct.global.converter.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Slf4j
public class Redirect {
    private static String redirectUrl = null;

    private Redirect() {

    }

    public static String getInstance() {
        if (redirectUrl == null) {
            redirectUrl = getRedirectUrl();
        }
        return redirectUrl;
    }

    private static String getRedirectUrl() {
        String url = RedirectType.REDIRECT_ERROR.getUrl();
        List<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.contains(RoleType.ROLE_ADMIN.getType())) url = RedirectType.REDIRECT_ADMIN.getUrl();
        if (roles.contains(RoleType.ROLE_USER.getType())) url = RedirectType.REDIRECT_USER.getUrl();
        return url;
    }
}
