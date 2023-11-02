package org.fastcampus.proejct.auth.converter.response;

import org.fastcampus.proejct.auth.converter.dto.KakaoAccountDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public record KakaoResponse(
        Long id,
        LocalDateTime connectedAt,
        KakaoAccountDto kakaoAccountDto,
        Map<String, Object> attributes
) {
    public static KakaoResponse from(Map<String, Object> attributes) {
        return new KakaoResponse(
                Long.valueOf(String.valueOf(attributes.get("id"))),
                LocalDateTime.parse(
                        String.valueOf(attributes.get("connected_at")),
                        DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
                ),
                KakaoAccountDto.from((Map<String, Object>) attributes.get("kakao_account")),
                (Map<String, Object>) attributes.get("properties")
        );
    }

    public String email() {
        return this.kakaoAccountDto().email();
    }

    public String nickname() {
        return this.kakaoAccountDto().nickname();
    }
}
