package org.fastcampus.proejct.global.exception;

import org.fastcampus.proejct.base.converter.Api;

import java.util.Arrays;

public class ExceptionMapper {
    public static Api.Error get(Exception e) {
        var errorMessages = Arrays.stream(e.getStackTrace()).map(it -> {
            var format = "%s : { %s } -> %s";
            return String.format(format, it.getClassName(), it.getMethodName(), it.getLineNumber());
        }).toList();

        return Api.Error.builder()
                .errorMessages(errorMessages)
                .build();
    }
}
