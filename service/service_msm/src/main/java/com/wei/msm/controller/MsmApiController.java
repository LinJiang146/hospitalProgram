package com.wei.msm.controller;


import com.wei.common.result.Result;
import com.wei.msm.service.MailService;
import com.wei.msm.service.MsmService;
import com.wei.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) throws Exception {
        //从redis获取验证码，如果获取获取到，返回ok
        // key phoneCode+手机号  value 验证码

        if (!"18579983326".equals(phone)) return Result.fail("该服务为阿里云短信测服务，只有指定手机号可接收到短信");

        String code = redisTemplate.opsForValue().get("phoneCode" + phone);

        //如果从redis获取不到，
        // 生成验证码，
        if (StringUtils.isEmpty(code)) {
            code = RandomUtil.getSixBitRandom();
            //调用service方法，通过整合短信服务进行发送
            boolean send = msmService.send(phone, code);
            //生成验证码放到redis里面，设置有效时间
            if (send){
                redisTemplate.opsForValue().set("phoneCode"+phone,code,5*60,TimeUnit.SECONDS);
            }else return Result.fail("短信发送失败");
        }

        return Result.ok(code);
    }

    //发送邮箱验证码
    @GetMapping("sendMail/{email}")
    public Result sendMailCode(@PathVariable String email) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key phoneCode+手机号  value 验证码
        String code = redisTemplate.opsForValue().get("mailCode" + email);

        //如果从redis获取不到，
        // 生成验证码，
        if (StringUtils.isEmpty(code)) {
            code = RandomUtil.getSixBitRandom();
            //调用service方法，通过整合短信服务进行发送
            boolean send = mailService.send(email, code);
            //生成验证码放到redis里面，设置有效时间
            if (send){
                redisTemplate.opsForValue().set("mailCode" + email,code,5*60,TimeUnit.SECONDS);
            }else return Result.fail("邮件发送失败");
        }

        return Result.ok(code);
    }


}
