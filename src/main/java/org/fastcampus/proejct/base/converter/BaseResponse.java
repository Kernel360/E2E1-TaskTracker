package org.fastcampus.proejct.base.converter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record BaseResponse<T>(
        int code,
        String message,
        T data
) {

}
