package com.wei.order.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wei.enums.RefundStatusEnum;
import com.wei.model.order.PaymentInfo;
import com.wei.model.order.RefundInfo;
import com.wei.order.mapper.RefundInfoMapper;
import com.wei.order.service.RefundInfoService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RefundInfoServiceImpl
        extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {


    @Autowired
    private RedissonClient redissonClient;

    //保存退款记录
    @Override
    public RefundInfo saveRefundInfo(PaymentInfo paymentInfo) {
        //通过redission分布式锁，实现生成订单方法的幂等性
        RLock lock = redissonClient.getLock("refund:" + paymentInfo.getOrderId());
        lock.lock();
        try {
            //判断是否有重复数据添加
            QueryWrapper<RefundInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id",paymentInfo.getOrderId());
            wrapper.eq("payment_type",paymentInfo.getPaymentType());
            RefundInfo refundInfo = baseMapper.selectOne(wrapper);
            if(refundInfo != null) {//有相同数据
                return refundInfo;
            }
            //添加记录
            refundInfo = new RefundInfo();
            refundInfo.setCreateTime(new Date());
            refundInfo.setOrderId(paymentInfo.getOrderId());
            refundInfo.setPaymentType(paymentInfo.getPaymentType());
            refundInfo.setOutTradeNo(paymentInfo.getOutTradeNo());
            refundInfo.setRefundStatus(RefundStatusEnum.UNREFUND.getStatus());
            refundInfo.setSubject(paymentInfo.getSubject());
            refundInfo.setTotalAmount(paymentInfo.getTotalAmount());
            baseMapper.insert(refundInfo);
            return refundInfo;
        }catch (Exception e){
            return null;
        }finally {
            lock.unlock();
        }


    }
}
