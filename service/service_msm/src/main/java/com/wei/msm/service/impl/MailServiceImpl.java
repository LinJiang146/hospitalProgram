package com.wei.msm.service.impl;

import com.wei.model.msm.MyEMail;
import com.wei.msm.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean send(String EMail, String code) {
        String subject = "校医院预约挂号系统邮箱验证码登录";
        String content = "登录验证码为："+code+"。有效期为5分钟。";
        String[] tos = new String[]{EMail};
        MyEMail myEMail = new MyEMail(subject,content,tos);
        return commonEmail(myEMail);
    }

    public boolean commonEmail(MyEMail myEMail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(myEMail.getTos());
        //邮件标题
        message.setSubject(myEMail.getSubject());
        //邮件内容
        message.setText(myEMail.getContent());
        try {
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
