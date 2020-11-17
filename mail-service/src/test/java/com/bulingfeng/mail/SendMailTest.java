package com.bulingfeng.mail;

import com.bulingfeng.MailApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Author:bulingfeng
 * @Date: 2020-11-17
 */
public class SendMailTest extends MailApplicationTests {

    @Autowired
    private JavaMailSender mailSender;


    @Test
    public void sendTest(){
        SimpleMailMessage message = new SimpleMailMessage();
        //注意这里的发送人邮箱，要与yml配置中的username相同，否则验证不通过
        message.setFrom("企业邮箱账号");

        message.setTo("需要发送的邮箱账号");
        //这里添加抄送人名称列表 没有的话可以忽略
        String[] ccList = new String[]{"xxxx@126.com", "yang.junming@xxxx.com"};
//        message.setCc(ccList);
        //这里添加密送人名称列表
        String[] bccList = new String[]{"yyyy@126.com", "yjmyzz@xxxx.com"};
//        message.setBcc(bccList);
        message.setSubject("测试发送-springboot-mail");
        message.setText("realme");
        mailSender.send(message);
        System.out.println("发送成功！");
    }
}
