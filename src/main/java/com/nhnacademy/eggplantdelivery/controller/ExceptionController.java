package com.nhnacademy.eggplantdelivery.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외를 처리하는 Controller 입니다.
 *
 * @author : 김훈민, 조재철
 * @since 1.0
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * validation 통과를 하지 못한 필드를 바인딩하여 반환하는 역할을 하는 클래스 입니다.
     *
     * @param ex validation 통과 하지 못한 에러에 대한 객체 입니다.
     * @return BindException 에러 발생시 ResponseEntity 타입으로 반환.
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleException(BindException ex) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", ex.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}
