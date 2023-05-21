package com.wei.order.receiver;


import com.rabbitmq.client.Channel;
import com.wei.common.constant.RabbitMQConst;
import com.wei.order.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderReceiver {

    @Autowired
    private OrderService orderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConst.QUEUE_TASK_8, durable = "true"),
            exchange = @Exchange(value = RabbitMQConst.EXCHANGE_DIRECT_TASK),
            key = {RabbitMQConst.ROUTING_TASK_8}
    ))
    public void patientTips(Message message, Channel channel) throws IOException {
        orderService.patientTips();
    }

}
