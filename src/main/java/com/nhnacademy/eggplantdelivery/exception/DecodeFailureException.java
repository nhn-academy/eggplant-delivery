package com.nhnacademy.eggplantdelivery.exception;

/**
 * 암호화에 실패할 경우 예외처리 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public class DecodeFailureException extends IllegalArgumentException {

    private static final String ENCODE_ERROR_MESSAGE = "정보 암호화에 실패했습니다.";

    public DecodeFailureException() {
        super(ENCODE_ERROR_MESSAGE);
    }
}
