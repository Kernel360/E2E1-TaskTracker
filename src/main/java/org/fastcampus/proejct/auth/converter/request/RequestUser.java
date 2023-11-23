package org.fastcampus.proejct.auth.converter.request;

public record RequestUser(
        String username,
        String password,
        String nickname
) {
    public static RequestUser of(String username, String password, String nickname) {
        return new RequestUser(username, password, nickname);
    }
}
