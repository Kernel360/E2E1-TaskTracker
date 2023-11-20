package org.fastcampus.proejct.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(10)
@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Api> exception(
            Exception e
    ) {
        log.error("", e);
        var response = Api.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("서버 오류")
                .data(e.getMessage())
                .error(ExceptionMapper.get(e))
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(response);
    }
}
