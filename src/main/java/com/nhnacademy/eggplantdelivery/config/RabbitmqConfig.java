package com.nhnacademy.eggplantdelivery.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq와 관련된 설정을 하기 위한 클래스.
 *
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqConfig {

    public static final String ROUTING_EGGPLANT = "routing.Eggplant";
    public static final String ROUTING_TRACKING_NO = "routing.TrackingNo";

    private String host;
    private int port;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * RabbitMq 연결 설정을 하기위한 클래스.
     *
     * @param authenticationConfig secure key 와 관련된 설정을 위한 객체.
     * @return 연결 설정하는 ConnectionFactory 반환.
     * @throws UnrecoverableKeyException key 를 복원할 수 없는 경우에 예외를 발생.
     * @throws CertificateException      인증서의 encode 문제, 유효하지 않은 경우 예외 발생.
     * @throws KeyStoreException         키스토어 예외 발생.
     * @throws IOException               I/O 오류가 발생하는 경우에 throw 되는 예외 발생.
     * @throws NoSuchAlgorithmException  암호 알고리즘이 요구되었음에도 불구하고, 현재의 환경에서는 사용 가능하지 않은 경우에 예외 발생.
     * @throws KeyManagementException    키 관리를 다루는 모든 작업에 대한 일반적인 키 관리 예외 발생.
     */
    @Bean
    public ConnectionFactory connectionFactory(final AuthenticationConfig authenticationConfig)
        throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException,
        NoSuchAlgorithmException, KeyManagementException {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(authenticationConfig.findSecretDataFromSecureKeyManager(host));
        connectionFactory.setPort(port);
        connectionFactory.setUsername(authenticationConfig.findSecretDataFromSecureKeyManager(username));
        connectionFactory.setPassword(authenticationConfig.findSecretDataFromSecureKeyManager(password));

        return connectionFactory;
    }

    /**
     * 메시지 객체를 json 타입으로 변환 하기 위해 messageConverter 로 Jackson2JsonMessageConverter 를 이용하게끔 빈으로 등록하는 메서드.
     *
     * @return message converter 로 Jackson2JsonMessageConverter 를 반환.
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue queueEggplant() {
        return new Queue("queue.Eggplant", false);
    }

    @Bean
    Queue queueTrackingNo() {
        return new Queue("queue.TrackingNo", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding bindEggplant(final Queue queueEggplant,
                         final DirectExchange exchange) {

        return BindingBuilder.bind(queueEggplant)
                             .to(exchange)
                             .with(ROUTING_EGGPLANT);
    }

    @Bean
    Binding bindTrackingNo(final Queue queueTrackingNo,
                           final DirectExchange exchange) {

        return BindingBuilder.bind(queueTrackingNo)
                             .to(exchange)
                             .with(ROUTING_TRACKING_NO);
    }

}
