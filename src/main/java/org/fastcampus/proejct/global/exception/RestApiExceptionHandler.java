package org.fastcampus.proejct.global.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@Order(1)
@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(NoSuchElementException e) {
        log.error("", e);
        var response = Api.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("데이터가 존재하지 않습니다.")
                .error(ExceptionMapper.get(e))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Api> usernameNotFound(UsernameNotFoundException e) {
        log.error("", e);
        var response = Api.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("사용자가 존재하지 않습니다.")
                .error(ExceptionMapper.get(e))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(response);
    }
}
