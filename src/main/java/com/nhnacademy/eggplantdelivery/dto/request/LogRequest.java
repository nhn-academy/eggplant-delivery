package com.nhnacademy.eggplantdelivery.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NHN Log & Crash 의 기록을 위한 요청 클래스입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LogRequest {

    private String projectName;
    private String projectVersion;
    private String logVersion;
    private String body;
    private String logSource;
    private String logType;
    private String localhost;

}
