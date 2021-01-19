package com.ifp.sendmq.Controller;

import com.ifp.common.Model.BaseMessage;
import com.ifp.common.Model.MQName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessageController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    public String sendMessage(){
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setFunctionCode("sendMessage");
        baseMessage.setFunctionName("发送mq消息");
        Map map = new HashMap<>();
        map.put("name", "明天");
        map.put("sex", "男");
        map.put("age", "27");
        baseMessage.setJson(map.toString());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTemplate.convertAndSend(MQName.test.EXCHANGE, MQName.test.ROUTING_key, baseMessage, correlationData);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "sendMessage1", method = RequestMethod.GET)
    public String sendMessage1(){
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setFunctionCode("sendMessage");
        baseMessage.setFunctionName("发送mq消息");
        Map map = new HashMap<>();
        map.put("name", "明天");
        map.put("sex", "男");
        map.put("age", "27");
        baseMessage.setJson("");

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        try {
            rabbitTemplate.convertAndSend("testQueue", MQName.test.ROUTING_key, baseMessage, correlationData);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }
}
