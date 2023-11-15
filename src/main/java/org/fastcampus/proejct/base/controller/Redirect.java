package org.fastcampus.proejct.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.RedirectType;
import org.fastcampus.proejct.base.converter.RoleType;
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
        List<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        log.info("Redirect : {}", roles);
        if (roles.contains(RoleType.ROLE_ADMIN.getType())) return RedirectType.REDIRECT_ADMIN.getUrl();
        return RedirectType.REDIRECT_USER.getUrl();
    }
}
