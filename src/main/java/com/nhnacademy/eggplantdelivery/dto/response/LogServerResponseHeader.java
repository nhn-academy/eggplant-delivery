package com.nhnacademy.eggplantdelivery.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NHN 로그서버의 응답 객체를 담은 클래스입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class LogServerResponseHeader {

    private boolean successful;
    private Integer resultCode;
    private String resultMessage;

}
