package com.kyle.web;

import com.kyle.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendEmail {
    Logger log = LoggerFactory.getLogger(SendEmail.class);
    @Resource
    private EmailUtils emailUtils;

    @RabbitListener(queuesToDeclare = @Queue("topic.message"))    //监听器监听指定的Queue
    public void process1(String emailanduid) {
        String[] userEmail =emailanduid.split(",");
        String s = emailUtils.sendMail(userEmail[0],userEmail[1]);
    }
}
