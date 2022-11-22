package com.example.SpringRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private RabbitTemplate template2;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message){
        message.setMessageId("1234");
        message.setMessageDate(new Date());
        template.convertAndSend("message_exchange", "message_routing_key_1", message);
        template.convertAndSend("message_exchange", "message_routing_key_2", message);
        template.convertAndSend("message_exchange", "message_routing_key_3", message);
        return "message published";
    }
}
