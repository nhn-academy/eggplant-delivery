package com.nhnacademy.eggplantdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 관련 설정을 하기 위한 클래스.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger 설정의 핵심으로 문서화 객체를 빈으로 등록하는 메서드.
     *
     * @return Api 문서화 객체 반환.
     * @author 김훈민, 조재철
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.nhnacademy.eggplantdelivery"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * Swagger API 문서에 대한 설명을 표기하는 메소드.
     *
     * @return 해당 Api 설명 정보 반환.
     * @author 김훈민, 조재철
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spring Boot Open Api Test with Swagger")
            .description("설명 부분")
            .version("1.0.0")
            .build();
    }
}
