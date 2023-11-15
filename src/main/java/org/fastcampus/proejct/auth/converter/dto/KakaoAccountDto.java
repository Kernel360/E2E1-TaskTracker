package org.fastcampus.proejct.auth.converter.dto;


import java.util.Map;

public record KakaoAccountDto(
        String email,
        Profile profile
) {
    public static KakaoAccountDto from(
            Map<String, Object> attributes
    ) {
        return new KakaoAccountDto(
                String.valueOf(attributes.get("email")),
                Profile.from((Map<String, Object>) attributes.get("profile"))
        );
    }

    public record Profile(String nickname) {
        public static Profile from(Map<String, Object> attributes) {
            return new Profile(String.valueOf(attributes.get("nickname")));
        }
    }

    public String nickname() {
        return this.profile().nickname();
    }
}
