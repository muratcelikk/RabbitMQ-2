package com.mrtclk.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Murat Çelik on Kas, 2021
 */
@Configuration
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.queue.name}")
    String queueName;

    @Value("${spring.rabbitmq.exchange.name}")
    String exchange;

    @Value("${spring.rabbitmq.routingkey.name}")
    String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);

    }

    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange) { //constructor Injection
        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }
}
