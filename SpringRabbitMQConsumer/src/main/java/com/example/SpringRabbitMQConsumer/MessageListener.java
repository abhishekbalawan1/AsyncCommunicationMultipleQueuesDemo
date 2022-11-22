package com.example.SpringRabbitMQConsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "message_queue_1")
    public void listener(CustomMessage message){
        System.out.println(message.toString());
    }

    @RabbitListener(queues = "message_queue_2")
    public void listener2(CustomMessage message){
        System.out.println(message.toString()+" This message recieved from second queue.");
    }

    @RabbitListener(queues = "message_queue_3")
    public void listener3(CustomMessage message){
        System.out.println(message.toString()+" This message recieved from third queue.");
    }
}
