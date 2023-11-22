package org.fastcampus.proejct.global.config;

import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.auth.converter.response.KakaoResponse;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService
    ) throws Exception {
        return http
                .formLogin(login -> login
                        .loginPage("/login-form").permitAll()
                        .defaultSuccessUrl("/", true)
                        .failureForwardUrl("/login-form")
                )
                .formLogin(withDefaults())
                .logout(it -> it.logoutSuccessUrl("/"))
                .oauth2Login(auth -> auth.userInfoEndpoint(it -> it.userService(oAuth2UserService)))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(antMatcher("/error")).permitAll()
                        .requestMatchers(antMatcher("/api/**")).permitAll()
//                        .requestMatchers(antMatcher("/**")).permitAll()
                        .requestMatchers(antMatcher("/board/**")).hasRole("USER")
                        .requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
            UserInfoService service,
            PasswordEncoder encoder
    ) {
        final DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();
        return userRequest -> {
            OAuth2User user = defaultService.loadUser(userRequest);
            KakaoResponse kakaoResponse = KakaoResponse.from(user.getAttributes());
            String email = kakaoResponse.email();
            String dummyPassword = encoder.encode("{bcrypt}" + UUID.randomUUID());
            String nickname = kakaoResponse.nickname();
            return service.getUserInfo(email)
                    .map(UserPrincipal::from)
                    .orElseGet(() ->
                            UserPrincipal.from(
                                    service.saveUser(email, dummyPassword, nickname)
                            ));
        };
    }

    @Bean
    public UserDetailsService userDetailsService(UserInfoService service) {
        return username -> service
                .getUserInfo(username)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
