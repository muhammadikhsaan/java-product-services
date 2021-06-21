package com.product.configuration.rabbitmq;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RabbitMQConfiguration {

    public static final String SERVICE_PRODUCT_ROUTING = "PRODUCT.QTY.CHANGES";
    public static final String SERVICE_ORDER_ROUTING = "ORDER.PRODUCT.QTY";

    public static final String SERVICE_PRODUCT_EXCHANGE = "SERVICE_PRODUCT_EXCHANGE";

    public static final String SERVICE_PRODUCT_QUEUE = "SERVICE_PRODUCT_QUEUE";
    public static final String SERVICE_ORDER_QUEUE = "SERVICE_ORDER_QUEUE";

    @Bean
    public Queue queue() {
        return new Queue(SERVICE_PRODUCT_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(SERVICE_PRODUCT_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SERVICE_PRODUCT_ROUTING);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
