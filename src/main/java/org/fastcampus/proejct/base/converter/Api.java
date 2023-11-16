package org.fastcampus.proejct.base.converter;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public record Api<T>(
        int code,
        String message,
        T data
) {

}
