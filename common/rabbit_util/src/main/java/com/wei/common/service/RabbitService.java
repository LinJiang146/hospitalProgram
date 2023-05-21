package com.wei.common.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public boolean sendMessage(String exchange, String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        return true;
    }

}
