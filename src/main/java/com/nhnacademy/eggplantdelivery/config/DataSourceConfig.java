package com.nhnacademy.eggplantdelivery.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mysql DB 설정을 위한 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * MySql 설정을 위한 빈 입니다.
     *
     * @param authenticationConfig secure key 와 관련된 설정을 위한 객체.
     * @return 연결 설정하는 ConnectionFactory 반환.
     */
    @Bean
    public DataSource getDataSource(AuthenticationConfig authenticationConfig) {
        String secretUrl = authenticationConfig.findSecretDataFromSecureKeyManager(url);
        String secretPassword = authenticationConfig.findSecretDataFromSecureKeyManager(password);

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(secretUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(secretPassword);

        return dataSourceBuilder.build();
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
