package com.nhnacademy.eggplantdelivery.exception;

/**
 * 복호화 실패할 경우 예외처리 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public class EncodeFailureException extends RuntimeException {

    private static final String DECODE_ERROR_MESSAGE = "정보 복호화에 실패했습니다.";

    public EncodeFailureException() {
        super(DECODE_ERROR_MESSAGE);
    }
}
