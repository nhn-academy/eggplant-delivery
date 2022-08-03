package com.nhnacademy.eggplantdelivery.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mysql DB 설정을 위한 클래스
 *
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource getDataSource(AuthenticationConfig authenticationConfig)
        throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException,
        NoSuchAlgorithmException, KeyManagementException {
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
