package com.example.SpringRabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Bean
    public Queue queue_1(){
        return new Queue("message_queue_1");
    }

    @Bean
    public Queue queue_2(){
        return new Queue("message_queue_2");
    }

    @Bean
    public Queue queue_3(){
        return new Queue("message_queue_3");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("message_exchange");
    }

    @Bean
    public Binding biding_1(Queue queue_1, TopicExchange exchange){
        return BindingBuilder.
                bind(queue_1).
                to(exchange).
                with("message_routing_key_1");
    }

    @Bean
    public Binding biding_2(Queue queue_2, TopicExchange exchange){
        return BindingBuilder.
                bind(queue_2).
                to(exchange).
                with("message_routing_key_2");
    }

    @Bean
    public Binding biding_3(Queue queue_3, TopicExchange exchange){
        return BindingBuilder.
                bind(queue_3).
                to(exchange).
                with("message_routing_key_3");
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
