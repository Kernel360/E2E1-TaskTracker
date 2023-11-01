package org.fastcampus.proejct.global.jpa;

import org.fastcampus.proejct.user.converter.dto.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * SecurityContextHolder : Soring Security의 정보가 담겨 있다.
 * getAuthentication : 로그인 여부 확인
 * getPrincipal : 로그인 정보인 Principal 정보를 가져온다.
 * cast : Principal 정보를 UserPrincipal 타입으로 casting
 * getUsername : username 추출
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserPrincipal.class::cast)
                .map(UserPrincipal::getUsername);
    }
}
