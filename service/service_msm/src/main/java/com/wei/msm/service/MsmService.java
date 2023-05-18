package com.wei.msm.service;


import com.wei.vo.msm.MsmVo;

public interface MsmService {

    //发送手机验证码
    boolean send(String phone, String code) throws Exception;

    //mq使用发送短信
    boolean send(MsmVo msmVo);
}
