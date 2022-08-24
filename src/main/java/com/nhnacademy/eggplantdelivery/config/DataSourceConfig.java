package com.nhnacademy.eggplantdelivery.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Mysql DB 설정을 위한 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {
    private final AuthenticationConfig authenticationConfig;
    @Value("${datasource.classname}")
    private String classname;

    @Value("${datasource.master}")
    private String master;

//    @Value("${datasource.locale}")
//    private String locale;
    @Value("${datasource.slave}")
    private String slave;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;


    @Bean(name = "master")
    public DataSource masterDataSource() {

        return getDataSource(authenticationConfig.findSecretDataFromSecureKeyManager(master),
            authenticationConfig.findSecretDataFromSecureKeyManager(password));
    }

    @Bean(name = "slave")
    public DataSource slaveDataSource() {
        return getDataSource(slave,
            authenticationConfig.findSecretDataFromSecureKeyManager(password));
    }

//    @Bean(name = "locale")
//    public DataSource localeDataSource() {
//        return getDataSource(locale,
//            authenticationConfig.findSecretDataFromSecureKeyManager(password));
//    }

    /**
     * MySql 설정을 위한 빈 입니다.
     *
     * @param secretUrl      secure key 와 관련된 설정을 위한 객체.
     * @param secretPassword
     * @return 연결 설정하는 ConnectionFactory 반환.
     */
    private DataSource getDataSource(String secretUrl, String secretPassword) {
        Properties properties = new Properties();
        properties.setProperty("url", secretUrl);
        properties.setProperty("user", username);
        properties.setProperty("password", secretPassword);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSourceClassName(classname);
        hikariConfig.setMaximumPoolSize(2);
        hikariConfig.setDataSourceProperties(properties);

        return new HikariDataSource(hikariConfig);
    }
}
