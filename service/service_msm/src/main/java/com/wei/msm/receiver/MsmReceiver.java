//package com.wei.msm.receiver;
//
//
//import com.wei.msm.service.MsmService;
//import com.wei.vo.msm.MsmVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MsmReceiver {
//
//    @Autowired
//    private MsmService smsService;
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = MqConst.QUEUE_MSM_ITEM, durable = "true"),
//            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_MSM),
//            key = {MqConst.ROUTING_MSM_ITEM}
//    ))
//    public void send(MsmVo msmVo, Message message, Channel channel) {
//        smsService.send(msmVo);
//    }
//}
