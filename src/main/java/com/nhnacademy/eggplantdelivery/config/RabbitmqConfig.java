package com.nhnacademy.eggplantdelivery.config;

import static com.nhnacademy.eggplantdelivery.constant.ExchangeConstant.DIRECT_EXCHANGE;
import static com.nhnacademy.eggplantdelivery.constant.ExchangeConstant.DIRECT_EXCHANGE_DLX;
import static com.nhnacademy.eggplantdelivery.constant.QueueConstant.CHANGE_DELIVERY_STATUS;
import static com.nhnacademy.eggplantdelivery.constant.QueueConstant.CHANGE_DELIVERY_STATUS_DLX;
import static com.nhnacademy.eggplantdelivery.constant.QueueConstant.REQUEST_TRACKING_NO;
import static com.nhnacademy.eggplantdelivery.constant.QueueConstant.REQUEST_TRACKING_NO_DLX;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_CHANGE_DELIVERY_STATUS;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_CHANGE_DELIVERY_STATUS_DLX;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_REQUEST_TRACKING_NO;
import static com.nhnacademy.eggplantdelivery.constant.RoutingKeyConstant.ROUTING_REQUEST_TRACKING_NO_DLX;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq와 관련된 설정을 하기 위한 클래스.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqConfig {

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
     * RabbitMq 연결 설정을 하기위한 빈 입니다.
     *
     * @param authenticationConfig secure key 와 관련된 설정을 위한 객체.
     * @return 연결 설정하는 ConnectionFactory 반환.
     * @author 김훈민, 조재철
     */
    @Bean
    public ConnectionFactory connectionFactory(final AuthenticationConfig authenticationConfig) {

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
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    Queue queueRequestTrackingNo() {
        Map<String, Object> withArguments = getWithArguments();

        withArguments.put("x-dead-letter-exchange", DIRECT_EXCHANGE_DLX.getValue());
        withArguments.put("x-dead-letter-routing-key", ROUTING_REQUEST_TRACKING_NO_DLX.getValue());
        return new Queue(REQUEST_TRACKING_NO.getValue(), false, false, false, withArguments);
    }

    @Bean
    Queue queueRequestTrackingNoDlx() {
        Map<String, Object> withArguments = getWithArguments();

        return new Queue(REQUEST_TRACKING_NO_DLX.getValue(), false, false, false, withArguments);
    }

    @Bean
    Queue queueChangeDeliveryStatus() {
        Map<String, Object> withArguments = getWithArguments();

        withArguments.put("x-dead-letter-exchange", DIRECT_EXCHANGE_DLX.getValue());
        withArguments.put("x-dead-letter-routing-key", ROUTING_CHANGE_DELIVERY_STATUS_DLX.getValue());
        return new Queue(CHANGE_DELIVERY_STATUS.getValue(), false, false, false, withArguments);
    }

    @Bean
    Queue queueChangeDeliveryStatusDlx() {
        Map<String, Object> withArguments = getWithArguments();

        return new Queue(CHANGE_DELIVERY_STATUS_DLX.getValue(), false, false, false, withArguments);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE.getValue());
    }

    @Bean
    DirectExchange exchangeDlx() {
        return new DirectExchange(DIRECT_EXCHANGE_DLX.getValue());
    }

    @Bean
    Binding bindRequestTrackingNo(final Queue queueRequestTrackingNo,
                                  final DirectExchange exchange) {

        return BindingBuilder.bind(queueRequestTrackingNo)
                             .to(exchange)
                             .with(ROUTING_REQUEST_TRACKING_NO.getValue());
    }

    @Bean
    Binding bindRequestTrackingNoDlx(final Queue queueRequestTrackingNoDlx,
                                     final DirectExchange exchangeDlx) {

        return BindingBuilder.bind(queueRequestTrackingNoDlx)
                             .to(exchangeDlx)
                             .with(ROUTING_REQUEST_TRACKING_NO_DLX.getValue());

    }

    @Bean
    Binding bindChangeDeliveryStatus(final Queue queueChangeDeliveryStatus,
                                     final DirectExchange exchange) {

        return BindingBuilder.bind(queueChangeDeliveryStatus)
                             .to(exchange)
                             .with(ROUTING_CHANGE_DELIVERY_STATUS.getValue());
    }

    @Bean
    Binding bindChangeDeliveryStatusDlx(final Queue queueChangeDeliveryStatusDlx,
                                        final DirectExchange exchangeDlx) {

        return BindingBuilder.bind(queueChangeDeliveryStatusDlx)
                             .to(exchangeDlx)
                             .with(ROUTING_CHANGE_DELIVERY_STATUS_DLX.getValue());
    }

    private Map<String, Object> getWithArguments() {
        return new HashMap<>();
    }

}
