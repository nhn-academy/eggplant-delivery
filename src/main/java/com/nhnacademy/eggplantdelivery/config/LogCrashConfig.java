package com.nhnacademy.eggplantdelivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "log-crash")
@Configuration
public class LogCrashConfig {
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Bean
    public String getRawProjectName(AuthenticationConfig authenticationConfig) {
        return authenticationConfig.findSecretDataFromSecureKeyManager(projectName);
    }

}
