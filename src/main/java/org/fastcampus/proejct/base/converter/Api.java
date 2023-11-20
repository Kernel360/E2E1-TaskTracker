package org.fastcampus.proejct.base.converter;

import jakarta.validation.Valid;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T> {
    private int code;

    private String message;

    @Valid
    private T data;

    private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Error {
        private List<String> errorMessages;
    }

}
