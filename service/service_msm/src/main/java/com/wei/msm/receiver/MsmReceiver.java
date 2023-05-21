package com.wei.msm.receiver;


import com.rabbitmq.client.Channel;
import com.wei.common.constant.RabbitMQConst;
import com.wei.msm.service.MsmService;
import com.wei.msm.service.impl.MsmServiceImpl;
import com.wei.vo.msm.MsmVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsmReceiver {


    @Autowired
    private MsmServiceImpl msmService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue( value = RabbitMQConst.QUEUE_MSM_ITEM,durable = "true"),
            exchange = @Exchange(value = RabbitMQConst.EXCHANGE_DIRECT_MSM),
            key = {RabbitMQConst.ROUTING_MSM_ITEM}
    ))
    public void send(MsmVo msmVo, Message message, Channel channel){
        msmService.send(msmVo);
    }

}
