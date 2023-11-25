package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${queue-data.name}")
    private String queueNameData;
    @Value("${routing-data.key}")
    private String routingKeyData;
    @Value("${exchange.name}")
    private String exchangeName;
    @Value("${queue-value.name}")
    private String queueNameValue;
    @Value("${routing-value.key}")
    private String routingKeyValue;

    @Bean
    public Queue queueData() {
        return new Queue(queueNameData);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingData() {
        return BindingBuilder.bind(queueData()).to(exchange()).with(routingKeyData);
    }

    @Bean
    public Queue queueValue() {
        return new Queue(queueNameValue);
    }

    @Bean
    public Binding bindingValue() {
        return BindingBuilder.bind(queueValue()).to(exchange()).with(routingKeyValue);
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }



}
