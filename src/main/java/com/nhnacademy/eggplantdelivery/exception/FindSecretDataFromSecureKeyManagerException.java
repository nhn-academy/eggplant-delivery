package com.nhnacademy.eggplantdelivery.exception;

/**
 * 시크릿 키설정 문제 발생시 예외처리 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public class FindSecretDataFromSecureKeyManagerException extends RuntimeException {

    private static final String MESSAGE = "시크릿 키 설정에 문제가 생겼습니다.";

    public FindSecretDataFromSecureKeyManagerException() {
        super(MESSAGE);
    }

}
